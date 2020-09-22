package com.mycompany.mytests;

import com.mycompany.myapp.Filter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static com.mycompany.variableconfig.VariableConfig.user_id;

public class FilterTests {
    Filter filter = new Filter();

    @Test
    public void readJsonAndFilterUsersWithin100Km() throws IOException, ParseException, JSONException {
        Assert.assertEquals(13, filter.usersWithin100Km().size());
    }

    @Test
    public void returnFilteredJsonInOrder() throws ParseException, IOException, JSONException {
        JSONArray filteredUsers = filter.usersWithin100KmInAscendingOrder();

        Assert.assertEquals("4", filteredUsers.getJSONObject(0).getString(user_id));
        Assert.assertEquals("39", filteredUsers.getJSONObject(12).getString(user_id));
    }
}
