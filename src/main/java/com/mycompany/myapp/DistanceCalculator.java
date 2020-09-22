package com.mycompany.myapp;

import static com.mycompany.variableconfig.VariableConfig.*;
import static java.lang.Math.*;
import static java.lang.Math.toRadians;

public class DistanceCalculator {
    public static String calculateDistance(String latitudeOfUser, String longitudeOfUser) {
        double latOfUserAsDouble = convertToDouble(latitudeOfUser);
        double longOfUserAsDouble = convertToDouble(longitudeOfUser);

        double deltaOfLongs = dublinOfficeLongitude - longOfUserAsDouble;

        double centralSubtendedAngle = acos(
                (sin(dublinOfficeLatitude) * sin(latOfUserAsDouble))
                + (cos(dublinOfficeLatitude) * cos(latOfUserAsDouble) * cos(deltaOfLongs)));

        double distanceInKm = round(radiusOfEarthInKm * toRadians(centralSubtendedAngle));

        return String.valueOf(distanceInKm);
    }

    protected static double convertToDouble(String value) {
        return Double.parseDouble(value);
    }
}
