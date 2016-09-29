package com.SemNomeAindaCartolaFC.Athletes;

import java.io.*;
import java.util.*;
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
	
	public void setMinMaxPrice(Double min, Double max) {
		this.minPrice = min;
		this.maxPrice = max;
	}
	
	public void setBaseFullPath(String jsonFilePath) {
		this.baseFullPath = jsonFilePath;
	}
	
	private void extractMinAndMaxFromBase() {
		

		try {
			JSONObject fullBase = BaseJSONParser.getJSONObjectFromFile(baseFullPath);
			Athlete[] athletes = getAllAthletesFrom(fullBase);
			Stream<Athlete> athleteStream = Arrays.stream(athletes);
			Double minMean = getMinMean(athleteStream);
			Double maxMean = getMaxMean(athleteStream);
			Double minVariation = getMinVariation(athleteStream);
			Double maxVariation = getMaxVariation(athleteStream);
			Double minPrice = getMinPrice(athleteStream);
			Double maxPrice = getMaxPrice(athleteStream);
			
			setMinMaxMean(minMean, maxMean);
			setMinMaxVariation(minVariation, maxVariation);
			setMinMaxPrice(minPrice, maxPrice);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private Double getMinMean(Stream<Athlete> athletes) {
		return athletes
				.reduce((athlete, lastAthlete) -> athlete.mean < lastAthlete.mean ? athlete : lastAthlete)
				.get().mean;
	}
	
	private Double getMaxMean(Stream<Athlete> athletes) {
		return athletes
				.reduce((athlete, lastAthlete) -> athlete.mean > lastAthlete.mean ? athlete : lastAthlete)
				.get().mean;
	}
	
	private Double getMinVariation(Stream<Athlete> athletes) {
		return athletes
				.reduce((athlete, lastAthlete) -> athlete.variation < lastAthlete.variation ? athlete : lastAthlete)
				.get().variation;
	}
	
	private Double getMaxVariation(Stream<Athlete> athletes) {
		return athletes
				.reduce((athlete, lastAthlete) -> athlete.variation > lastAthlete.variation ? athlete : lastAthlete)
				.get().variation;
	}	
	
	private Double getMinPrice(Stream<Athlete> athletes) {
		return athletes
				.reduce((athlete, lastAthlete) -> athlete.price < lastAthlete.price ? athlete : lastAthlete)
				.get().price;
	}
	
	private Double getMaxPrice(Stream<Athlete> athletes) {
		return athletes
				.reduce((athlete, lastAthlete) -> athlete.price > lastAthlete.price ? athlete : lastAthlete)
				.get().price;
	}
	
	private Athlete[] getAllAthletesFrom(JSONObject baseData) {
		JSONArray atletasArray = baseData.getJSONArray("atletas");
		int atletasArrayLength = atletasArray.length();
		AthleteFactory factory = new AthleteFactory();
		ArrayList<Athlete> allAthletes = new ArrayList<>();
		
		for(int i = 0; i < atletasArrayLength; i++) {
			JSONObject athleteData = atletasArray.getJSONObject(i);
			Athlete athlete = factory.createAthleteFrom(athleteData);
			allAthletes.add(athlete);
		}		
		
		
		Athlete[] athletesFlatArray = new Athlete[allAthletes.size()];
		return allAthletes.toArray(athletesFlatArray);
	}

}
