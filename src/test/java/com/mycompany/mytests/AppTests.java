package com.mycompany.mytests;

import com.mycompany.myapp.App;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class AppTests {
    App app = new App();

    @Test
    public void verifyUserAtIndexZero() throws IOException, ParseException {
        JSONObject exampleUser = createExampleUser();

        Assert.assertEquals(exampleUser.toJSONString(), app.returnValueAtIndexZero().toJSONString());
    }

    @Test
    public void calculatesDistanceCorrectly() {
        String exampleUserLatitude = "52.986375";
        String exampleUserLongitude = "-6.043701";
        String expectedDistanceInKm = "45.0";

        Assert.assertEquals(expectedDistanceInKm, app.distanceBetweenOfficeAndUser(exampleUserLatitude, exampleUserLongitude));
    }

    @SuppressWarnings("unchecked")
    private JSONObject createExampleUser() {
        JSONObject userTemplate = new JSONObject();

        userTemplate.put("latitude", "52.986375");
        userTemplate.put("user_id", 12);
        userTemplate.put("name", "Christina McArdle");
        userTemplate.put("longitude", "-6.043701");

        return userTemplate;
    }
}
