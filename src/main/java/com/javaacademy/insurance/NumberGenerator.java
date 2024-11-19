package com.javaacademy.insurance;

import java.util.Random;

public class NumberGenerator {
    public static final int BOUND = 100_000;
    public static String generateNumber() {
        Random random = new Random();
        int number = random.nextInt(BOUND);
        return String.format("SD№%05d", number);
    }
}
