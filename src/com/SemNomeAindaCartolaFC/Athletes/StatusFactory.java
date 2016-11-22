package com.SemNomeAindaCartolaFC.Athletes;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class StatusFactory {

    private String jsonBasePath = "";
    private final String STATUS = "status";

    public StatusFactory(String jsonBasePath) {
        this.jsonBasePath = jsonBasePath;
    }

    public Status[] getAllStatus() throws Exception {
        JSONObject statusData = BaseJSONParser.getJSONObjectFromFile(this.jsonBasePath);
        return getAllStatusFrom(statusData.getJSONObject(this.STATUS));
    }

    public Status[] getAllStatusFrom(JSONObject statusData) {
        ArrayList<Status> statuses = new ArrayList<>();

        Iterator<String> statusesKeys = statusData.keys();
        while(statusesKeys.hasNext()) {
            String key = statusesKeys.next();

            Status s = createStatusFrom(statusData.getJSONObject(key));
            statuses.add(s);
        }

        Status[] statusesArray = new Status[statuses.size()];
        return statuses.toArray(statusesArray);
    }

    public Status createStatusFrom(JSONObject statusInnerData) {
        Status s = new Status();
        s.id = statusInnerData.getInt("id");
        s.name = statusInnerData.getString("nome");
        return s;
    }
}
