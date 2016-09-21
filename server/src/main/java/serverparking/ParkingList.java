package serverparking;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ParkingList {
	private static final ArrayList<Parking> pList = new ArrayList<>();
	static AtomicInteger atom = new AtomicInteger();
 	
	  static {
	    // Create a list of parkings
	    pList.add(
	        new Parking(atom.incrementAndGet(),
	        "LleidaParking",
	        800,
	        2000,
	        100,
	        60,
	        "VSD",
	        12.52,
	        85.69)
	    );

	    pList.add(
	        new Parking(atom.incrementAndGet(),
	        "GironaParking",
	        800,
	        2000,
	        100,
	        50,
	        "VSD",
	        12.52,
	        85.69)
	    );

	    pList.add(
	        new Parking(atom.incrementAndGet(),
	        "BarcelonaParking",
	        800,
	        2000,
	        100,
	        1,
	        "LMXJVSD",
	        12.52,
	        85.69)
	    );
	  }
	  
	  public static ArrayList<Parking> getInstance(){
		    return pList;
	  }
}
