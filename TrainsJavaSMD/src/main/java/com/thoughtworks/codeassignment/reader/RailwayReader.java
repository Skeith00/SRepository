package com.thoughtworks.codeassignment.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RailwayReader {
	
	public static String[] routeReader(String fileName){
		List<String> array = new ArrayList<String>();
		BufferedReader in;
		String[] readedFile = null;
		try {
			in = new BufferedReader(new FileReader(fileName));
			String line;
			while((line = in.readLine()) != null)
			{
				array.add(line);
			}
			readedFile = array.toArray(new String[array.size()]);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return readedFile;
	}
}
