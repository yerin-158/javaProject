package com.company.factory;

public class RandomController {

    public static int randomInt(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
