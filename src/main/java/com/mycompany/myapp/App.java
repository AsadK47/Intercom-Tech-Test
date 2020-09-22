package com.mycompany.myapp;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.mycompany.variableconfig.VariableConfig.*;

public class App {
    public static void main(String[] args) throws ParseException, JSONException, IOException {
        App app = new App();
        System.out.println(app.returnFilteredJsonInOrder());
    }

    public JSONArray readJsonAndFilterUsersWithin100Km() throws IOException, ParseException, JSONException {
        JSONArray userArray = JsonFileReader.readJsonAndReturnArray();
        JSONArray filteredArray = new JSONArray();

        for (int i = 0; i < userArray.size(); i++) {
            org.json.JSONObject user = new org.json.JSONObject(userArray.get(i).toString());

            String userLat = user.getString(latitude);
            String userLong = user.getString(longitude);

            double distance = Double.parseDouble(DistanceCalculator.calculateDistance(userLat, userLong));

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
            @Override
            public int compare(org.json.JSONObject a, org.json.JSONObject b) {
                String valA = "";
                String valB = "";

                try {
                    valA = a.getString(user_id);
                    valB = b.getString(user_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return Integer.parseInt(valA) - Integer.parseInt(valB);
            }
        });

        for (int i = 0; i < unsortedArray.size(); i++) {
            sortedJsonArray.put(jsonValues.get(i));
        }

        return sortedJsonArray;
    }
}
