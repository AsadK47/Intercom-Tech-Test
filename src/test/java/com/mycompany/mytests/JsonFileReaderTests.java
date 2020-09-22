package com.mycompany.mytests;

import com.mycompany.myapp.JsonFileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class JsonFileReaderTests {
    JSONObject exampleUser = CalculateDistanceTests.createExampleUser();

    @Test
    public void verifyUserAtIndexZero() throws IOException, ParseException {
        Assert.assertEquals(exampleUser.toJSONString(), JsonFileReader.returnValueAtIndexZero().toJSONString());
    }
}
