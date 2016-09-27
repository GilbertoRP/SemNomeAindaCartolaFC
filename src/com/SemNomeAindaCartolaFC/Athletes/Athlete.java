package com.SemNomeAindaCartolaFC.Athletes;

import org.json.*;

public class Athlete {
	public String name;
	public Integer id;
	public String nick;
	public String photoURL;
	public Double price;
	public Double variation;
	public Double mean;
	public Integer gamesPlayed;
	public Integer position_id;
	public Double orderingKey;
	
	
	public String toString() {		
		JSONObject j = new JSONObject();
		j.put("name",name);
		j.put("id",id);
		j.put("nick",nick);
		j.put("photoURL",photoURL);
		j.put("price",price);
		j.put("variation",variation);
		j.put("mean",mean);
		j.put("gamesPlayed",gamesPlayed);
		j.put("position_id",position_id);
		j.put("orderingKey",orderingKey);
		
		return j.toString();
	}
}
