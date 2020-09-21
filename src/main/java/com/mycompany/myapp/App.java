package com.mycompany.myapp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class App {
    JSONParser jsonParser = new JSONParser();
    String filePath = "/Users/asadk/Projects/Extra-Projects/intercom-tech-test/customers.json";

    public JSONObject returnValueAtIndexZero() throws IOException, ParseException {
        JSONArray jsonArray = readJson();
        return (JSONObject) jsonArray.get(0);
    }

    private JSONArray readJson() throws IOException, ParseException {
        return (JSONArray) jsonParser.parse(new FileReader(filePath));
    }
}
