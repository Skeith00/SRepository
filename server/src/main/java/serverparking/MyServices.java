package serverparking;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Root resource (exposed at "myservices" path)
 */
@Path("myservices")
public class MyServices {

	private final ArrayList<Parking> pList = ParkingList.getInstance();
    
	/**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type. 
     */
	
    //Method to add a new parking
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("add")
	public Response addParking(@Context UriInfo allUri) {
        MultivaluedMap<String, String> mpAllQueParams = allUri.getQueryParameters();
        try{
        	int id = ParkingList.atom.incrementAndGet();
        	final Parking newParking = new Parking(id,
			mpAllQueParams.getFirst("name"),
			Integer.parseInt(mpAllQueParams.getFirst("openingTime")),
			Integer.parseInt(mpAllQueParams.getFirst("closingTime")),
			Integer.parseInt(mpAllQueParams.getFirst("totalPlaces")),
			Integer.parseInt(mpAllQueParams.getFirst("FreePlaces")),
			mpAllQueParams.getFirst("openDays"),
			Double.parseDouble(mpAllQueParams.getFirst("latitudeGPS")),
			Double.parseDouble(mpAllQueParams.getFirst("longitudeGPS")));
    		pList.add(newParking);
        }catch(NullPointerException e){
    		return Response
    				   .status(0)
    				   .entity("Some fields missed").build();
    	}catch(NumberFormatException e){
    		return Response
    				   .status(0)
    				   .entity("Some fields missed or with incorrect format").build();
    	}catch(Exception e){
    		return Response
    				   .status(0)
    				   .entity(e.toString()).build();
    	}

		return Response
				   .status(200)
				   .entity("Parking added with the id number:"+ParkingList.atom.get()).build();
	}
    
    //Method to modify a indicated parking
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("modify")
	public Response modifyParking(@Context UriInfo allUri){
        MultivaluedMap<String, String> mpAllQueParams = allUri.getQueryParameters();

        try{
        	int id = Integer.parseInt(mpAllQueParams.getFirst("id"));
        	for (Parking parking: pList) {
        		if(parking.getId() == id){
        			if(mpAllQueParams.getFirst("name")!=null)
        				parking.setName(mpAllQueParams.getFirst("name"));
            		if(mpAllQueParams.getFirst("openingTime")!=null)            			
            			parking.setOpeningTime(Integer.parseInt(mpAllQueParams.getFirst("openingTime")));
                	if(mpAllQueParams.getFirst("closingTime")!=null)
                		parking.setClosingTime(Integer.parseInt(mpAllQueParams.getFirst("closingTime")));
                    if(mpAllQueParams.getFirst("totalPlaces")!=null)
                    	parking.setTotalPlaces(Integer.parseInt(mpAllQueParams.getFirst("totalPlaces")));
                    if(mpAllQueParams.getFirst("FreePlaces")!=null)
                    	parking.setFreePlaces(Integer.parseInt(mpAllQueParams.getFirst("FreePlaces")));
                    if(mpAllQueParams.getFirst("openDays")!=null)
                    	parking.setOpenDays(mpAllQueParams.getFirst("openDays"));
                    if(mpAllQueParams.getFirst("latitudeGPS")!=null)
                    	parking.setLatitudeGPS(Double.parseDouble(mpAllQueParams.getFirst("latitudeGPS")));
                    if(mpAllQueParams.getFirst("longitudeGPS")!=null)
                    	parking.setLongitudeGPS(Double.parseDouble(mpAllQueParams.getFirst("longitudeGPS")));
        			}
        		return Response
      				   .status(200)
      				   .entity("Parking has been modified").build();
        	}
    		return Response
    				   .status(0)
    				   .entity("Nonexistent parking").build(); 
        }catch(Exception e){
    		return Response
 				   .status(0)
 				   .entity(e.toString()).build();
        }
	}
    
    /*Method to list all the different parking, including a few filters as well
     * completo=false: Show the noncomplete parkings
     * dia=DD.MM.YYYY#hh: Show the open parkings in a specific day and time
     */
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("allparkings")
	public Response getParkings(@QueryParam("completo") Boolean completo, @QueryParam("dia") String dia){

    	try{
	    	StringBuffer str = new StringBuffer();
	    	
	    	completo=(completo==null)?true:false;
	    	String[] splittedDay=(dia!=null)?dia.split("[.#]"):null;
	    	Calendar calendar = null;
    		if(splittedDay!=null){
    			if(splittedDay.length == 4){
    				calendar = new GregorianCalendar(Integer.parseInt(splittedDay[2]), Integer.parseInt(splittedDay[1]), Integer.parseInt(splittedDay[0]), Integer.parseInt(splittedDay[3]), 00);
    			}
    			else{			
    				return Response
 					   .status(0)
 					   .entity("incorrect date format").build();
    			}
    		}

	    	for (Parking parking: pList) {
	    		if(!completo){
	    			if(parking.getFreePlaces()>0)
	    				str.append(parking.getId() + ". " +parking.getName()+" | From "+parking.getOpeningTime()+" till "+parking.closingTime+" : "+parking.getOpenDays()+" | Free spaces: "+parking.getFreePlaces()+"\n");
	    		}
	    		else if(calendar!=null){
    				if(parking.getOpenDays().contains(weekDay(calendar))&(parking.getOpeningTime()<time(calendar)&parking.getClosingTime()>time(calendar)) )
    					str.append(parking.getId() + ". " +parking.getName()+" | From "+parking.getOpeningTime()+" till "+parking.closingTime+" : "+parking.getOpenDays()+" | Free spaces: "+parking.getFreePlaces()+"\n");
	    		}
	    		else if(parking.getOpenDays().contains(weekDay(Calendar.getInstance()))&(parking.getOpeningTime()<time(Calendar.getInstance())&parking.getClosingTime()>time(Calendar.getInstance())) ){
	    			str.append(parking.getId() + ". " +parking.getName()+" | From "+parking.getOpeningTime()+" till "+parking.closingTime+" : "+parking.getOpenDays()+" | Free spaces: "+parking.getFreePlaces()+"\n");
	    		}
	    	}
	    	if(str.toString().equals("")){
				return Response
						   .status(200)
						   .entity("No parkings listed").build();
	    	}else{
			return Response
					   .status(200)
					   .entity(str.toString()).build();
	    	}
    	}catch(Exception e){
			return Response
					   .status(0)
					   .entity(e.toString()).build();
    	}
	}
    
    //Method to get the day of the week 
    private static CharSequence weekDay(Calendar calendar){
    	CharSequence[] days={"L","M","X","J","V","S","D"};
        int numberDay=0;
        numberDay=calendar.get(Calendar.DAY_OF_WEEK);
        return days[numberDay - 1];
    }
    
    //Method to get the time in the format we need 
    private static int time(Calendar calendar){
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minuts = calendar.get(Calendar.MINUTE);        
        
        String stringMinuts=(String.valueOf(minuts).length()==1)?"0"+String.valueOf(minuts):String.valueOf(minuts);        
        return Integer.valueOf(String.valueOf(hour) + stringMinuts);
    }

    /*Method to liberate a place parking from a indicated parking,
     * increasing the FreePlaces in 1	*/
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("liberateparkings")
	public Response liberateParking(@QueryParam("id") final int id){
    	try{
	    	for(Parking parking : pList){
	    		if(parking.getId() == id){
	    			if(parking.getFreePlaces()>0){
	    				parking.setFreePlaces(parking.getFreePlaces()+1);
	    	    		return Response
	    	    				   .status(200)
	    	    				   .entity("The parking place has been liberated").build();
	    			}
	    			else{
	    	    		return Response
	 	    				   .status(0)
	 	    				   .entity("All the parkings are allready free").build();
	    			}
	    		}
	    	}
			return Response
					   .status(0)
					   .entity("Nonexistent parking").build();
    	}catch(Exception e){
			return Response
					   .status(0)
					   .entity(e.toString()).build();
    	}	
	}
    
    /*Method to occupy a place parking from a indicated parking,
     * reducing the attribute FreePlaces in 1	*/
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("occupyparking")
	public Response occupyParking(@QueryParam("id") int id ){
    	for(Parking parking : pList){
    		if(parking.getId() == id){
    			if(parking.getFreePlaces()>0){
    				parking.setFreePlaces(parking.getFreePlaces()-1);
    	    		return Response
    	    				   .status(200)
    	    				   .entity("The parking place has been occupied").build();
    			}
    			else{
    	    		return Response
 	    				   .status(0)
 	    				   .entity("No free parkings").build();
    			}
    		}
    	}
		return Response
				   .status(0)
				   .entity("Nonexistent parking").build();    			
	}
}
