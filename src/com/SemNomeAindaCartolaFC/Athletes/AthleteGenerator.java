package com.SemNomeAindaCartolaFC.Athletes;

public class AthleteGenerator {
	
	private Double minMean = 0.0;
	private Double maxMean = 0.0;
	private Double minVariation = 0.0;
	private Double maxVariation = 0.0;
	private Double minPrice = 0.0;
	private Double maxPrice = 0.0;
	
	
	public AthleteGenerator() {
		
	}
	
	public Athlete generateAthlete() {
		Athlete genAthlete = new Athlete();
		
		//TODO: Implement algorithm to generate random properties to athlete
		
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
}
