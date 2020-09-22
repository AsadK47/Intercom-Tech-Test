package com.mycompany.mytests;

import com.mycompany.myapp.CalculateDistance;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static com.mycompany.variableconfig.VariableConfig.latitude;
import static com.mycompany.variableconfig.VariableConfig.longitude;

public class CalculateDistanceTests {
    JSONObject exampleUser = createExampleUser();

    @Test
    public void calculatesDistanceCorrectly() {
        String expectedDistanceInKm = "45.0";
        String errorMessage = String.format("Double check the formula to calculate distance, " +
                "the expected outcome was %s", expectedDistanceInKm);
        String exampleLat = exampleUser.get(latitude).toString();
        String exampleLong = exampleUser.get(longitude).toString();

        Assert.assertEquals(errorMessage, expectedDistanceInKm,
                CalculateDistance.usingGivenFormula(exampleLat, exampleLong));
    }

    @SuppressWarnings("unchecked")
    public static JSONObject createExampleUser() {
        JSONObject userTemplate = new JSONObject();

        userTemplate.put("latitude", "52.986375");
        userTemplate.put("user_id", 12);
        userTemplate.put("name", "Christina McArdle");
        userTemplate.put("longitude", "-6.043701");

        return userTemplate;
    }
}
