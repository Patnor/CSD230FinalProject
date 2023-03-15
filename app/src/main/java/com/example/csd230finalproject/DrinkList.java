package com.example.csd230finalproject;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DrinkList {
    public DrinkList(){

    }

    @SerializedName("drinks")
    private List<Drink> myDrinks;

    public List<Drink> getMyDrinks() {
        return myDrinks;
    }

    public void setMyDrinks(List<Drink> myDrinks) {
        this.myDrinks = myDrinks;
    }
}