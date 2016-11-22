package com.SemNomeAindaCartolaFC.Athletes;

import org.json.*;
import com.SemNomeAindaCartolaFC.DB.*;

import java.io.Serializable;
import java.lang.reflect.Field;


public class Athlete extends DataIdentifiable implements Serializable {
	
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

	private static final int SPACING = 12;
	private static final int NAMESPACING = 60;

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public String toFormatedField() throws IllegalAccessException {

		String data = "";
		Field[] fields = Athlete.class.getFields();

		for(int i = 0; i < fields.length; i++) {

			if (!fields[i].getName().equalsIgnoreCase("photoURL")) {

				int CURRENTSPACING = fields[i].getName().equalsIgnoreCase("name") ? NAMESPACING : SPACING;

				if (fields[i].get(this) != null) {
					data += String.format("%" + CURRENTSPACING + "s",fields[i].get(this).toString());
				}
				else data += String.format("%" + CURRENTSPACING + "s","null");

				if (i < fields.length - 1) data += "|";
			}
		}

		return data;
	}

	public static String toColumnFields() {
		String fieldSet = "";
		Field[] fields = Athlete.class.getFields();
		for(int i = 0; i < fields.length; i++) {

			if (!fields[i].getName().equalsIgnoreCase("photoURL")) {

				int CURRENTSPACING = fields[i].getName().equalsIgnoreCase("name") ? NAMESPACING : SPACING;

				fieldSet += String.format("%" + CURRENTSPACING + "s",fields[i].getName());

				if (i < fields.length - 1) {
					fieldSet += "|";
				}
			}
		}

		return fieldSet;
	}

	public String toString() {
		JSONObject j = new JSONObject();
		
		j.put("id",id);
		j.put("position_id",position_id);
		j.put("club_id",club_id);
		j.put("status_id",status_id);
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
