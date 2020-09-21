package com.mycompany.myapp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

import static com.mycompany.variableconfig.VariableConfig.*;
import static java.lang.Math.*;

public class App {
    public JSONObject returnValueAtIndexZero() throws IOException, ParseException {
        JSONArray jsonArray = readJsonAndReturnArray();
        return (JSONObject) jsonArray.get(0);
    }

    private JSONArray readJsonAndReturnArray() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        String filePath = "/Users/asadk/Projects/Extra-Projects/intercom-tech-test/customers.json";
        return (JSONArray) jsonParser.parse(new FileReader(filePath));
    }

    public String calculatingTheDistance(String latitudeOfUser, String longitudeOfUser) {
        double latOfUserAsDouble = convertToDouble(latitudeOfUser);
        double longOfUserAsDouble = abs(convertToDouble(longitudeOfUser));
        double differenceInLongs = dublinOfficeLongitude - longOfUserAsDouble;

        double centralSubtendedAngle = acos(
                (sin(dublinOfficeLatitude) * sin(latOfUserAsDouble))
                + (cos(dublinOfficeLatitude) * cos(latOfUserAsDouble) * cos(differenceInLongs)));

        double distanceInKm = round(radiusOfEarthInKm * toRadians(centralSubtendedAngle));

        return String.valueOf(distanceInKm);
    }

    private double convertToDouble(String value) {
        return Double.parseDouble(value);
    }
}
