package com.SemNomeAindaCartolaFC.Athletes;
	
import org.json.*;

public class AthleteFactory {
	
	public Athlete createAthleteFrom(JSONObject athleteData) {
		Athlete athlete = new Athlete();
		athlete.name = athleteData.getString("name");
		athlete.id = athleteData.getInt("id");
		athlete.nick = athleteData.getString("nick");
		athlete.photoURL = athleteData.getString("photoURL");
		athlete.price = athleteData.getDouble("price");
		athlete.variation = athleteData.getDouble("variation");
		athlete.mean = athleteData.getDouble("mean");
		athlete.gamesPlayed = athleteData.getInt("gamesPlayed");
		athlete.position_id = athleteData.getInt("position_id");
		athlete.orderingKey = athleteData.getDouble("orderingKey");
		return athlete;
	}
}
