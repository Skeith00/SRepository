package serverparking;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyServicesTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();
        target = c.target(Main.BASE_URI);
    }

    @SuppressWarnings("deprecation")
	@After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void menu() {
		System.out.println("Adding new parking...");
    	addFormTest();
		System.out.println("Listing parkings open currently...");
    	getParkingsTest();
		System.out.println("Modifying Parking...");
    	modifyFormTest();
		System.out.println("Listing open parkings on the date 23.10.2016 02:00...");
    	getParkingsTest("23.10.2016#23");
		System.out.println("Listing parkings with free places...");
    	getParkingsTest(false);
		System.out.println("Occuping a parking place...");
    	occupyParkingTest();
		System.out.println("Listing parkings with free places...");
    	getParkingsTest(false);
		System.out.println("Liberating parking place..");
    	liberateParkingTest();
		System.out.println("Listing parkings with free places...");
    	getParkingsTest(false);
    }
    
    public void addFormTest(){
		Response response = target.path("myservices/add").
		queryParam("name", "TarragonaParking").
		queryParam("openingTime", "900").
		queryParam("closingTime", "2100").
		queryParam("totalPlaces", "50").
		queryParam("FreePlaces", "10").
		queryParam("openDays", "VSD").
		queryParam("latitudeGPS", "85.34").
		queryParam("longitudeGPS", "74.25").
		request(MediaType.TEXT_PLAIN).
		get();
		String responseStr = response.readEntity(String.class);
		System.out.println(responseStr);
	}
    
    public void modifyFormTest(){    	    	
    	Response response = target.path("myservices/modify").
    	queryParam("id", "1").
		/*queryParam("name", "ValenciaParking").
		queryParam("openingTime", "900").
		queryParam("closingTime", "2100").
		queryParam("totalPlaces", "50").
		queryParam("FreePlaces", "10").
		queryParam("openDays", "LMXJVSD").
		queryParam("latitudeGPS", "85.34").
		queryParam("longitudeGPS", "74.25").*/
		queryParam("openingTime", "900").
		queryParam("closingTime", "2100").
		queryParam("totalPlaces", "50").
		queryParam("FreePlaces", "10").
		queryParam("openDays", "LMXJVSD").
		request(MediaType.TEXT_PLAIN).
		get();
		String responseStr = response.readEntity(String.class);
		System.out.println(responseStr);
	}
    
    public void liberateParkingTest(){
    	Response response = target.path("myservices/liberateparkings").
		queryParam("id", "1").
		request(MediaType.TEXT_PLAIN).
		get();
		String responseStr = response.readEntity(String.class);
		System.out.println(responseStr);
	}
    
    public void occupyParkingTest(){
    	Response response = target.path("myservices/occupyparking").
		queryParam("id", "1").
		request(MediaType.TEXT_PLAIN).
		get();
		String responseStr = response.readEntity(String.class);
		System.out.println(responseStr);
	}
    
    public void getParkingsTest(){
		Response response = target.path("myservices/allparkings").
		request(MediaType.TEXT_PLAIN).
		get();
		String responseStr = response.readEntity(String.class);
		System.out.println(responseStr);
    }
    
    public void getParkingsTest(Boolean completo){
		Response response = target.path("myservices/allparkings").
		queryParam("completo", completo.toString()).
    	request(MediaType.TEXT_PLAIN).get();
		
		String responseStr = response.readEntity(String.class);
		System.out.println(responseStr);
    }
    
    public void getParkingsTest(String dia){
		Response response = target.path("myservices/allparkings").
    	queryParam("dia", dia).request(MediaType.TEXT_PLAIN).get();
		
		String responseStr = response.readEntity(String.class);
		System.out.println(responseStr);			
    }
}
