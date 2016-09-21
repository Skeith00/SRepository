package serverparking;

public class Parking {
	
	int id;
	String name;
	int openingTime;
	int closingTime;
	int totalPlaces;
	int FreePlaces;
	String openDays;
	double latitudeGPS;
	double longitudeGPS;
	
	public Parking(int id, String name, int openingTime, int closingTime, int totalPlaces, int FreePlaces,	
			String openDays, double latitudeGPS, double longitudeGPS) {
		this.id = id;
		this.name = name;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.totalPlaces = totalPlaces;
		this.FreePlaces = FreePlaces;
		this.openDays = openDays;
		this.latitudeGPS = latitudeGPS;
		this.longitudeGPS = longitudeGPS;
	}
	
	public Parking(){
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOpeningTime() {
		return openingTime;
	}
	public void setOpeningTime(int openingTime) {
		this.openingTime = openingTime;
	}
	public int getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(int closingTime) {
		this.closingTime = closingTime;
	}
	public int getTotalPlaces() {
		return totalPlaces;
	}
	public void setTotalPlaces(int totalPlaces) {
		this.totalPlaces = totalPlaces;
	}
	public int getFreePlaces() {
		return FreePlaces;
	}
	public void setFreePlaces(int freePlaces) {
		FreePlaces = freePlaces;
	}
	public String getOpenDays() {
		return openDays;
	}
	public void setOpenDays(String openDays) {
		this.openDays = openDays;
	}
	public double getLatitudeGPS() {
		return latitudeGPS;
	}
	public void setLatitudeGPS(double latitudeGPS) {
		this.latitudeGPS = latitudeGPS;
	}
	public double getLongitudeGPS() {
		return longitudeGPS;
	}
	public void setLongitudeGPS(double longitudeGPS) {
		this.longitudeGPS = longitudeGPS;
	}
}
