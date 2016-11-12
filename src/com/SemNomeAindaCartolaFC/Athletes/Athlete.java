package com.SemNomeAindaCartolaFC.Athletes;

import org.json.*;
import com.SemNomeAindaCartolaFC.DB.*;
public class Athlete extends Identifiable {
	
	public Integer id;
	public Integer position_id;
	public Integer club_id;
	public Integer status_id;
	public String name;
	public String nick;
	public String photoURL;
	public Double price;
	public Double variation;
	public Double mean;
	public Integer gamesPlayed;	
	public Double orderingKey;
	
	@Override
	public Integer getId() {
		return this.id;
	}
	public String toString() {		
		JSONObject j = new JSONObject();
		
		j.put("id",id);
		j.put("position_id",position_id);
		j.put("club_id",club_id);
		j.put("name",name);
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
