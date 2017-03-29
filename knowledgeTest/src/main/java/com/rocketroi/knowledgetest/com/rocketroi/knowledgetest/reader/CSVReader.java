package main.java.com.rocketroi.knowledgetest.com.rocketroi.knowledgetest.reader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.java.com.rocketroi.knowledgetest.com.rocketroi.knowledgetest.bean.Cat;
import main.java.com.rocketroi.knowledgetest.com.rocketroi.knowledgetest.bean.Diet;
import main.java.com.rocketroi.knowledgetest.com.rocketroi.knowledgetest.bean.Dog;
import main.java.com.rocketroi.knowledgetest.com.rocketroi.knowledgetest.bean.Pet;

public class CSVReader {
	
	private String csvFile;
	private String cvsSplitBy;
	
	public CSVReader(String csvFileParam, String cvsSplitByParam) {
		csvFile = csvFileParam;
		cvsSplitBy = cvsSplitByParam;
	}

	public void reader(Dog jukes, Cat feeder){
	    String line = "";
	    
	    try(InputStream stream = new FileInputStream(csvFile)) {
	    	BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
	        while ((line = br.readLine()) != null) {
	            String[] combo = line.split(cvsSplitBy);
	            if(combo.length >= 2){
	            	String params = (combo.length>2?combo[2]:null);
		            if(combo[0].equals(jukes.getName()))
		            	executeCommands(jukes, combo[1], params);
		            else if(combo[0].equals(feeder.getName()))
		            	executeCommands(feeder, combo[1], params);
	            }
	            else{
	            	System.out.println("Formato de linea en CSV incorrecto");
	            }
	        }
	
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	}
	
	public void executeCommands(Pet name, String action, String params){
		if(action.equals("Comer"))
			name.eat(Diet.valueOf(params));
		else if(action.equals("Beber"))
			name.drink(params);
		else if(action.equals("Jugar"))
			name.play(Integer.parseInt(params));
		else if(action.equals("Dormir"))
			name.sleep();
	}
}
