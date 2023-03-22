package com.example.appone;
import android.util.Pair;


public class Game {
    private int number_length;
    private String secret;


    public Game(int number_length){
        secret = "";
        this.number_length = number_length;
        while(secret.length()< number_length){
            int x = (int)(Math.random()*9);
            if(!secret.contains(String.valueOf(x)))
                secret +=String.valueOf(x);
        }
        secret = secret;
    }
    Pair<Integer,Integer> checking_number(String guessing_number){
        int cows = 0;
        int bulls = 0;
        for(int i = 0; i<this.number_length; i++){
            char c = secret.charAt(i);
            int k = guessing_number.indexOf(c);
            if (k < 0)
                continue;
            if  (k == i)
                bulls++;
            else
                cows++;
        }
        return new Pair(bulls, cows);
    }
    private boolean validate(String guessing_number){
        String check="";
        for(int i=0; i<this.number_length; i++) {
            String c = String.valueOf(guessing_number.charAt(i));
            if (check.contains(c)) {
                return false;
            }
            else
                check+=c;

        }
        if (guessing_number.length()==this.number_length)
            return true;
        else
            return false;
    }

}
