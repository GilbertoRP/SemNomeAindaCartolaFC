package com.SemNomeAindaCartolaFC.Athletes;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class ClubFactory {

    private String jsonBasePath = "";
    private final String CLUBES = "clubes";

    public ClubFactory(String jsonBasePath) {
        this.jsonBasePath = jsonBasePath;
    }

    public Club[] getAllClubs() throws Exception {
        JSONObject clubsData = BaseJSONParser.getJSONObjectFromFile(this.jsonBasePath);
        return getAllClubsFrom(clubsData.getJSONObject(this.CLUBES));
    }

    private Club[] getAllClubsFrom(JSONObject clubsData) {

        ArrayList<Club> clubs = new ArrayList<>();

        Iterator<String> clubsKeys = clubsData.keys();
        while(clubsKeys.hasNext()) {
            String key = clubsKeys.next();

            Club c = createClubFrom(clubsData.getJSONObject(key));
            clubs.add(c);
        }

        Club[] clubsArray = new Club[clubs.size()];
        return clubs.toArray(clubsArray);
    }

    private Club createClubFrom(JSONObject clubData) {

        Club c = new Club();
        c.id = clubData.getInt("id");
        c.name = clubData.getString("nome");
        c.abreviation = clubData.getString("abreviacao");

        return c;
    }
}
