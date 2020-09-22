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
        Assert.assertTrue(String.format("File %s not found", customers), customers.exists());
    }

    @Test
    public void verifyUserAtIndexZero() throws IOException, ParseException {
        Assert.assertEquals(exampleUser.toJSONString(), JsonFileReader.returnValueAtIndexZero().toJSONString());
    }
}
