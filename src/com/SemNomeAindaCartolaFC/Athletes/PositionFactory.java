package com.SemNomeAindaCartolaFC.Athletes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gilberto Ribeiro on 21/11/2016.
 */
public class PositionFactory {

    private String jsonBasePath = "";
    private final String POSICOES = "posicoes";

    public PositionFactory(String jsonBasePath) {
        this.jsonBasePath = jsonBasePath;
    }

    public Position[] getAllPositions() throws Exception {
        JSONObject athletesJsonBase = BaseJSONParser.getJSONObjectFromFile(jsonBasePath);
        return getAllPositionsFromJson(athletesJsonBase);
    }

    private Position[] getAllPositionsFromJson(JSONObject jsonBase) throws Exception{


        ArrayList<Position> positions = new ArrayList<>();
        JSONObject positionsObject = jsonBase.getJSONObject(this.POSICOES);

        for (int i = 1; i < 7; i++) {


            try {
                JSONObject positionData = positionsObject.getJSONObject(String.valueOf(i));
                Position p = createPositionFrom(positionData);
                positions.add(p);
            }
            catch(JSONException je) {
                je.printStackTrace();
            }
        }

        Position[] positionsArray = new Position[positions.size()];
        return positions.toArray(positionsArray);
    }

    private Position createPositionFrom(JSONObject positionData) {

        Position p = new Position();
        /*            "id": 1,
            "nome": "Goleiro",
            "abreviacao": "gol"
        * */
        p.id = positionData.getInt("id");
        p.name = positionData.getString("nome");
        p.abreviation = positionData.getString("abreviacao");

        return p;
    }
}
