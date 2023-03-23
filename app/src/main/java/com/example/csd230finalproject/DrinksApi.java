package com.example.csd230finalproject;


import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface DrinksApi {

    @GET("filter.php?a=Alcoholic")
    Call<JsonObject> getJsonDrinks();
    @GET("filter.php?a=Alcoholic")
    Call<List<Drink>> getDrinks();

    @GET("lookup.php?i=")
    Call<Drink> getDrink(@Query("id") String myId);

    @GET("lookup.php?i={id}")
    Call<DrinkList> getNewDrink(@Path("id") String myId);


    @GET("filter.php?a=Alcoholic")
    Call<DrinkList> getDrinkList();


    @GET
    Call<DrinkList> getNextDrink(@Url String url);

    @GET
    Call<DrinkList> getSearchDrink(@Url String url);
}