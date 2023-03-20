package com.example.csd230finalproject;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DrinkList implements Serializable {
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


/*    @SerializedName("drinks")
    private Drink myDrink;

    public Drink getMyDrink() {
        return myDrink;
    }

    public void setMyDrink(Drink myDrink) {
        this.myDrink = myDrink;
    }*/


}