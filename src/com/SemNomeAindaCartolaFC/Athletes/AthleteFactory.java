package com.SemNomeAindaCartolaFC.Athletes;
	
import org.json.*;

public class AthleteFactory {
	
	public Athlete createAthleteFrom(JSONObject athleteData) {
		return new Athlete();
	}
}
