package com.mycompany.myapp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

import static com.mycompany.variableconfig.VariableConfig.customerFilePath;

public class JsonFileReader {
    public static JSONObject returnValueAtIndexZero() throws IOException, ParseException {
        JSONArray jsonArray = readJsonAndReturnArray();
        return (JSONObject) jsonArray.get(0);
    }

    protected static JSONArray readJsonAndReturnArray() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        return (JSONArray) jsonParser.parse(new FileReader(customerFilePath));
    }
}
