package main.java.com.rocketroi.knowledgetest.com.rocketroi.knowledgetest.bean;

public class Dog extends Pet{

	private String id;
	private Size size;
	
	public Dog(String name, int age, String skinColor, String eyesColor) {
		super(name, age, skinColor, eyesColor);
		// TODO Auto-generated constructor stub
	}
	
	public void play(int time){
		long startTime = System.currentTimeMillis();
		System.out.println("Hola soy " + getName() + " y estoy jugando");
		while((System.currentTimeMillis()-startTime)<(time*1000)){
			try{
				bark();
			}catch (InterruptedException e) {
				System.out.println(getName() + " no tiene voz");
			}
		}
	}
	
	public void bark() throws InterruptedException{
		Thread.sleep(5000);
		System.out.println("Hola soy " + getName() + " y estoy Ladrando");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}	
}
