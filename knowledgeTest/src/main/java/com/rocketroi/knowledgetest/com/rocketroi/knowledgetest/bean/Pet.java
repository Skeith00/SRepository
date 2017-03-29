package main.java.com.rocketroi.knowledgetest.com.rocketroi.knowledgetest.bean;

public abstract class Pet {
	
	private String name;
	private int age;
	private String skinColor;
	private String eyesColor;
	private int numberOfLegs;
	
	public Pet(String name, int age, String skinColor, String eyesColor){
		this.name = name;
		this.age = age;
		this.skinColor = skinColor;
		this.eyesColor = eyesColor;
	}
	
	public void eat(Diet food){
		System.out.println("Hola soy " + getName() + " y estoy comiendo " +food);
	}
	
	public void drink(String drink){
		System.out.println("Hola soy " + getName() + " y estoy bebiendo "+drink);
	}
	
	public void sleep(){
		System.out.println("Hola soy " + getName() + " y estoy durmiendo");

		try {
			Thread.sleep(200*1000);

			//Thread.sleep(200*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Hola soy " + getName() + " y estoy despierto");
	}
	
	public void play(int time){
		System.out.println("Hola soy " + getName() + " y estoy jugando");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSkinColor() {
		return skinColor;
	}

	public void setSkinColor(String skinColor) {
		this.skinColor = skinColor;
	}

	public String getEyesColor() {
		return eyesColor;
	}

	public void setEyesColor(String eyesColor) {
		this.eyesColor = eyesColor;
	}

	public int getNumberOfLegs() {
		return numberOfLegs;
	}

	public void setNumberOfLegs(int numberOfLegs) {
		this.numberOfLegs = numberOfLegs;
	}
}
