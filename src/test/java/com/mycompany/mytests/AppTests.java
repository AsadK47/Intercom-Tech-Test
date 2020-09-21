package com.mycompany.mytests;

import com.mycompany.myapp.App;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class AppTests {
    @Test
    public void verifyJsonArray() throws IOException, ParseException {
        JSONObject exampleUser = createExampleUser();
        App app = new App();

        Assert.assertEquals(exampleUser.toJSONString(), app.returnValueAtIndexZero().toJSONString());
    }

    private org.json.simple.JSONObject createExampleUser() {
        org.json.simple.JSONObject userTemplate = new org.json.simple.JSONObject();

        userTemplate.put("latitude", "52.986375");
        userTemplate.put("user_id", 12);
        userTemplate.put("name", "Christina McArdle");
        userTemplate.put("longitude", "-6.043701");

        return userTemplate;
    }
}
