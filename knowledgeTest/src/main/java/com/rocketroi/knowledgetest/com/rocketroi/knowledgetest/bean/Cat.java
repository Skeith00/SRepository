package main.java.com.rocketroi.knowledgetest.com.rocketroi.knowledgetest.bean;

import java.util.ArrayList;

public class Cat extends Pet{

	private boolean alergic;
	private ArrayList<Diet> alergicFoodList =  new ArrayList<Diet>();


	public Cat(String name, int age, String skinColor, String eyesColor) {
		super(name, age, skinColor, eyesColor);
		// TODO Auto-generated constructor stub
	}
	
	public void play(int time){
		long startTime = System.currentTimeMillis();
		System.out.println("Hola soy " + getName() + " y estoy jugando");
		while((System.currentTimeMillis()-startTime)<(time*1000)){
			try{
				meow();
			}catch (InterruptedException e) {
				System.out.println(getName() + " no tiene voz");
			}
		}
	}	
	
	public void meow() throws InterruptedException{
		Thread.sleep(1000);
		System.out.println("Hola soy " + getName() + " y estoy Maullando");
	}
	
	public void eat(Diet food){
		if(alergic == true){
			boolean alergicallFood = false;
			for(Diet diet : alergicFoodList){
				if(diet.equals(food))
					alergicallFood = true;
			}

			if (alergicallFood == true)
				System.out.println("No puedo comer " + food + " porque soy alérgico");
			else
				System.out.println("Hola soy " + getName() + " y estoy comiendo " +food);

		}
		else{
			System.out.println("Hola soy " + getName() + " y estoy comiendo " +food);
		}
	}

	public boolean isAlergic() {
		return alergic;
	}

	public void setAlergic(boolean alergic) {
		this.alergic = alergic;
	}

	public void addAlergicFood(Diet food) {
		alergicFoodList.add(food);
	}

	public void setAlergicFoodList(ArrayList<Diet> alergicFoodList) {
		this.alergicFoodList = alergicFoodList;
	}
}
