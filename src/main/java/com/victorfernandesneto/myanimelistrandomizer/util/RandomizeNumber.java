package com.victorfernandesneto.myanimelistrandomizer.util;

import java.util.Random;

public class RandomizeNumber {
    public static int randomizeNumber(int ceil){
        Random rand = new Random();
        int randomNumber = rand.nextInt(ceil);
        return randomNumber;
    }
}
