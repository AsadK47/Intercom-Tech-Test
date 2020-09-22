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
        int numOfUsersWithin100Km = filter.usersWithin100Km().size();
        int expectedNumOfUsersWithFilter = 13;
        String errorMessage = String.format("Number of users not equal to %s", expectedNumOfUsersWithFilter);

        Assert.assertEquals(errorMessage ,expectedNumOfUsersWithFilter, numOfUsersWithin100Km);
    }

    @Test
    public void returnFilteredJsonInOrder() throws ParseException, IOException, JSONException {
        JSONArray filteredUsers = filter.usersWithin100KmInAscendingOrder();

        String expectedIdOfFirstUser = "4";
        String expectedIdOfLastUser = "39";

        String ifOfFirstUser = filteredUsers.getJSONObject(0).getString(user_id);
        String idOfLastUser = filteredUsers.getJSONObject(12).getString(user_id);

        String errorMessageForFirstUser = String.format("The id of the first user when filtering for " +
                "100 km in ascending order should be %s", expectedIdOfFirstUser);
        String errorMessageForLastUser = String.format("The id of the last user when filtering for " +
                "100 km in ascending order should be %s", expectedIdOfLastUser);

        Assert.assertEquals(errorMessageForFirstUser, expectedIdOfFirstUser, ifOfFirstUser);
        Assert.assertEquals(errorMessageForLastUser, expectedIdOfLastUser, idOfLastUser);
    }
}
