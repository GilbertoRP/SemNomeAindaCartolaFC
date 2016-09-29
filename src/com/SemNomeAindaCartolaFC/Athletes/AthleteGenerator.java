package com.SemNomeAindaCartolaFC.Athletes;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.json.*;

public class AthleteGenerator {
	
	private Double minMean = 0.0;
	private Double maxMean = 0.0;
	private Double minVariation = 0.0;
	private Double maxVariation = 0.0;
	private Double minPrice = 0.0;
	private Double maxPrice = 0.0;
	
	private String baseFullPath = "";
	
	public AthleteGenerator() {
		
	}
	
	public Athlete generateAthlete() {
		Athlete genAthlete = new Athlete();
		
		//TODO: Implement algorithm to generate random properties to athlete
		
		Random rand = new Random();
		
//		rand.net
		
		return genAthlete;
	}
	
	public void setMinMaxMean(Double min, Double max) {
		this.minMean = min;
		this.maxMean = max;
	}
	
	public void setMinMaxVariation(Double min, Double max) {
		this.minVariation = min;
		this.maxVariation = max;
	}
	
	public void setMinMaxPoints(Double min, Double max) {
		this.minPrice = min;
		this.maxPrice = max;
	}
	
	public void setBaseFullPath(String jsonFilePath) {
		this.baseFullPath = jsonFilePath;
	}
	
	private void extractMinAndMaxFromBase() {
		Path filePath = Paths.get(this.baseFullPath);
		try {
			Stream<String> lines = Files.lines(filePath);
			String jsonString = lines.reduce((line, lastLine) -> line + lastLine).get();			
			parseJsonString(jsonString);			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void parseJsonString(String jsonString) {
		JSONObject fullBase = new JSONObject(jsonString);
		JSONArray atletasArray = fullBase.getJSONArray("atletas");
		JSONObject firstAtleta = atletasArray.getJSONObject(0);
		System.out.println(firstAtleta.toString());
	}
	
	public static void main(String[] args) {
		AthleteGenerator generator = new AthleteGenerator();
		
		generator.setBaseFullPath("/home/gilberto/Projects/SemNomeAindaCartolaFC/data/25-09-2016.json");
		generator.extractMinAndMaxFromBase();
	}

}
