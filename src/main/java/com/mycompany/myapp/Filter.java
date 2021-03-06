package com.mycompany.myapp;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mycompany.variableconfig.VariableConfig.*;

public class Filter {
    public static void main(String[] args) throws ParseException, IOException, JSONException {
        Filter filter = new Filter();
        System.out.println(filter.usersWithin100KmInAscendingOrder());
    }

    public JSONArray usersWithin100Km() throws IOException, ParseException, JSONException {
        JSONArray userArray = JsonFileReader.readJsonAndReturnArray();
        JSONArray filteredArray = new JSONArray();

        for (int i = 0; i < userArray.size(); i++) {
            JSONObject user = new JSONObject(userArray.get(i).toString());

            String userLat = user.getString(latitude);
            String userLong = user.getString(longitude);

            double distance = CalculateDistance.convertToDouble(
                    CalculateDistance.usingGreatCircleFormula(userLat, userLong));

            if (distance <= 100) {
                JSONObject tempObject = new JSONObject();
                tempObject.put(user_id, user.getString(user_id));
                tempObject.put(name, user.getString(name));
                filteredArray.add(tempObject);
            }
        }

        return filteredArray;
    }

    public org.json.JSONArray usersWithin100KmInAscendingOrder() throws ParseException, JSONException, IOException {
        JSONArray unsortedArray = usersWithin100Km();
        org.json.JSONArray sortedJsonArray = new org.json.JSONArray();

        List<JSONObject> jsonValues = new ArrayList<>();

        for (int i = 0; i < unsortedArray.size(); i++) {
            jsonValues.add((JSONObject) unsortedArray.get(i));
        }

        jsonValues.sort((a, b) -> {
            int valA = 0;
            int valB = 0;

            try {
                valA = a.getInt(user_id);
                valB = b.getInt(user_id);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return valA - valB;
        });

        for (int i = 0; i < jsonValues.size(); i++) {
            sortedJsonArray.put(jsonValues.get(i));
        }

        return sortedJsonArray;
    }
}
