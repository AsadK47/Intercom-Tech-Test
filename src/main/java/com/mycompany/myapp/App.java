package com.mycompany.myapp;

import org.json.JSONException;
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

    public String distanceBetweenOfficeAndUser(String latitudeOfUser, String longitudeOfUser) {
        double latOfUserAsDouble = convertToDouble(latitudeOfUser);
        double longOfUserAsDouble = convertToDouble(longitudeOfUser);

        double deltaOfLongs = dublinOfficeLongitude - longOfUserAsDouble;

        double centralSubtendedAngle = acos(
                (sin(dublinOfficeLatitude) * sin(latOfUserAsDouble))
                        + (cos(dublinOfficeLatitude) * cos(latOfUserAsDouble) * cos(deltaOfLongs)));

        double distanceInKm = round(radiusOfEarthInKm * toRadians(centralSubtendedAngle));

        return String.valueOf(distanceInKm);
    }

    public JSONArray readJsonAndFilterUsersWithin100Km() throws IOException, ParseException, JSONException {
        JSONArray userArray = readJsonAndReturnArray();
        JSONArray filteredArray = new JSONArray();

        for (int i = 0; i < userArray.size(); i++) {
            org.json.JSONObject user = new org.json.JSONObject(userArray.get(i).toString());

            String userLat = user.getString("latitude");
            String userLong = user.getString("longitude");

            double distance = Double.parseDouble(distanceBetweenOfficeAndUser(userLat, userLong));

            if (distance <= 100) {
                org.json.JSONObject tempObject = new org.json.JSONObject();
                tempObject.put("user_id", user.getString("user_id"));
                tempObject.put("name", user.getString("name"));
                filteredArray.add(tempObject);
            }
        }

        return filteredArray;
    }

    private JSONArray readJsonAndReturnArray() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        String filePath = "customers.json";
        return (JSONArray) jsonParser.parse(new FileReader(filePath));
    }

    private double convertToDouble(String value) {
        return Double.parseDouble(value);
    }
}
