package com.mycompany.mytests;

import com.mycompany.myapp.Filter;
import com.mycompany.myapp.DistanceCalculator;
import com.mycompany.myapp.JsonFileReader;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static com.mycompany.variableconfig.VariableConfig.*;

public class FilterTests {
    Filter filter = new Filter();
    JSONObject exampleUser = createExampleUser();

    @Test
    public void verifyUserAtIndexZero() throws IOException, ParseException {
        Assert.assertEquals(exampleUser.toJSONString(), JsonFileReader.returnValueAtIndexZero().toJSONString());
    }

    @Test
    public void calculatesDistanceCorrectly() {
        String expectedDistanceInKm = "45.0";

        Assert.assertEquals(
                expectedDistanceInKm, DistanceCalculator.calculateDistance(
                        exampleUser.get(latitude).toString(), exampleUser.get(longitude).toString()));
    }

    @Test
    public void readJsonAndFilterUsersWithin100Km() throws IOException, ParseException, JSONException {
        Assert.assertEquals(13, filter.usersWithin100Km().size());
    }

    @Test
    public void returnFilteredJsonInOrder() {

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
