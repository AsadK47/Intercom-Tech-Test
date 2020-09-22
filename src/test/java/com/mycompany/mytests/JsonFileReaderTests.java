package com.mycompany.mytests;

import com.mycompany.myapp.JsonFileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class JsonFileReaderTests {
    JSONObject exampleUser = CalculateDistanceTests.createExampleUser();

    @Test
    public void checkCustomersJsonExists() {
        File customers = new File("customers.json");
        String errorMessage = String.format("File %s not found", customers);
        Assert.assertTrue(errorMessage, customers.exists());
    }

    @Test
    public void verifyUserAtIndexZero() throws IOException, ParseException {
        String firstUser = JsonFileReader.returnValueAtIndexZero().toJSONString();
        String errorMessage = String.format("Values don't match! Expected %s", exampleUser.toJSONString());
        Assert.assertEquals(errorMessage, exampleUser.toJSONString(), firstUser);
    }
}
