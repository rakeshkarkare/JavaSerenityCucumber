package com.utilities;


import io.restassured.internal.support.FileReader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;


public class JsonFileReader {

    private final static String FILEPATH = "./src/test/resources/TestData/testdata.json";

    public static JSONObject getJSONConfigData() {
        try{
            JSONObject json = new JSONObject(FileReader.readToString(new File(FILEPATH), "UTF-8"));
            DataStore.setJsonObject(json);
            return json;

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject getAuthInfo(String envkey){
        JSONObject json = getJSONConfigData();
        return json.getJSONObject(envkey);
    }

    public static Object getValue(String key) {
        JSONObject json = getJSONConfigData();
        Object val = json.get(key);
        if (val instanceof JSONArray){
            return json.getJSONArray(key);
        }
        else{
            return json.getString(key);
        }
    }
}
