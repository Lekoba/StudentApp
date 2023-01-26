package com.studentapp.utils;

import cucumber.api.java.ca.I;

import java.util.Random;

public class TestUtils {

    public static String getRandomValue()
    {
        Random random = new Random();
        int randomInt = random.nextInt(10000);

        return Integer.toString(randomInt);
    }
}
