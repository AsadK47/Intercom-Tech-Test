package com.mycompany.myapp;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.mycompany.variableconfig.VariableConfig.*;
import static java.lang.Math.*;

public class App {
    public static void main(String[] args) throws ParseException, JSONException, IOException {
        App app = new App();
        System.out.println(app.returnFilteredJsonInOrder());
    }

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

            String userLat = user.getString(latitude);
            String userLong = user.getString(longitude);

            double distance = Double.parseDouble(distanceBetweenOfficeAndUser(userLat, userLong));

            if (distance <= 100) {
                org.json.JSONObject tempObject = new org.json.JSONObject();
                tempObject.put(user_id, user.getString(user_id));
                tempObject.put(name, user.getString(name));
                filteredArray.add(tempObject);
            }
        }

        return filteredArray;
    }

    public org.json.JSONArray returnFilteredJsonInOrder() throws ParseException, JSONException, IOException {
        JSONArray unsortedArray = readJsonAndFilterUsersWithin100Km();
        org.json.JSONArray sortedJsonArray = new org.json.JSONArray();

        List<org.json.JSONObject> jsonValues = new ArrayList<>();

        for (int i = 0; i < unsortedArray.size(); i++) {
            jsonValues.add((org.json.JSONObject) unsortedArray.get(i));
        }

        Collections.sort(jsonValues, new Comparator<org.json.JSONObject>() {
            private static final String KEY_NAME = "user_id";

            @Override
            public int compare(org.json.JSONObject a, org.json.JSONObject b) {
                String valA = "";
                String valB = "";

                try {
                    valA = (String) a.get(KEY_NAME);
                    valB = (String) b.get(KEY_NAME);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return valA.compareTo(valB);

            }
        });

        for (int i = 0; i < unsortedArray.size(); i++) {
            sortedJsonArray.put(jsonValues.get(i));
        }

        return sortedJsonArray;
    }

    private JSONArray readJsonAndReturnArray() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        return (JSONArray) jsonParser.parse(new FileReader(customerFilePath));
    }

    private double convertToDouble(String value) {
        return Double.parseDouble(value);
    }
}
