package com.SemNomeAindaCartolaFC.Athletes;

public class Athlete {
	private String name;
	private Integer id;
	private String nick;
	private String photoURL;
	private Float price;
	private Float variation;
	private Float mean;
	private Integer gamesPlayed;
	private Integer position_id;
	private Float orderingKey;
	
	public Athlete(String name, Integer id) {
		this.name = name;
		this.id = id;
	}
	
	public Float getMean() {
		return this.mean;
	}
	
	public Float getVariation() {
		return this.variation;
	}
	
	public Float getPrice() {
		return this.price;
	}
}
