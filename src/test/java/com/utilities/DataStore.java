package com.utilities;
import org.json.JSONObject;

public class DataStore {

    private static JSONObject jsonObject;

    public static JSONObject getJsonObject(){
        return jsonObject;
    }

    public static void setJsonObject(JSONObject jsonObject){
        DataStore.jsonObject = jsonObject;
    }

    public static JSONObject getJsonObject(String key){
        return jsonObject.getJSONObject(key);
    }

    public static JSONObject getAuthInfo(String envKey){
        return jsonObject.getJSONObject(envKey);
    }

}
