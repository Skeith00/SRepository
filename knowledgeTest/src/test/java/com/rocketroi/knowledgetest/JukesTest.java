package test.java.com.rocketroi.knowledgetest;

import main.java.com.rocketroi.knowledgetest.com.rocketroi.knowledgetest.bean.Cat;
import main.java.com.rocketroi.knowledgetest.com.rocketroi.knowledgetest.bean.Diet;
import main.java.com.rocketroi.knowledgetest.com.rocketroi.knowledgetest.bean.Dog;
import main.java.com.rocketroi.knowledgetest.com.rocketroi.knowledgetest.bean.Size;
import main.java.com.rocketroi.knowledgetest.com.rocketroi.knowledgetest.reader.CSVReader;

import org.junit.Test;


public class JukesTest {

	Dog jukes = new Dog("Jukes", 3, "brown", "green");
	Cat feeder = new Cat("Feeder", 2, "grey", "blue");	

    @Test
    public void part1(){
		jukes.setId("C3P0");
		jukes.setSize(Size.XS);
		feeder.setAlergic(true);
		feeder.addAlergicFood(Diet.Jamón);

		feeder.play(120);
		feeder.drink("Agua");
		feeder.eat(Diet.Pan);
		feeder.eat(Diet.Jamón);
		feeder.sleep();

		jukes.play(120);
		jukes.drink("Agua");
		jukes.eat(Diet.Pan);
		jukes.eat(Diet.Jamón);
		jukes.sleep();
    }

    @Test
    public void part2(){
		feeder.setAlergic(true);
		feeder.addAlergicFood(Diet.Jamón);
	    String csvFile = "/knowledgeTest/knowledgeTest/src/main/resources/actions.csv";
	    String cvsSplitBy = ",";
    	CSVReader csvreader = new CSVReader(csvFile, cvsSplitBy);
	    csvreader.reader(jukes, feeder);
    }
}
