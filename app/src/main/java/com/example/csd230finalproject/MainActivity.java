package com.example.csd230finalproject;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.csd230finalproject.databinding.ActivityMainBinding;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Log.d("drinks", "onCreate after setContentView");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        //RequestInterface request = retrofit.create(RequestInterface.class);


        DrinksApi api = retrofit.create(DrinksApi.class);
        Call<DrinkList> call = api.getDrinkList();
        //Call<DrinkObj> call = api.getDrinkObj();

        call.enqueue(new Callback<DrinkList>() {
            @Override
            public void onResponse(Call<DrinkList> call, Response<DrinkList> response) {
                DrinkList myList = response.body();

                List<Drink> theDrinks = myList.getMyDrinks();
                Drink firstDrink = theDrinks.remove(0);
                Log.d("first Drink", firstDrink.getStrDrink());
            }

            @Override
            public void onFailure(Call<DrinkList> call, Throwable t) {

            }
        });
/*        call.enqueue(new Callback<DrinkObj>() {
            @Override
            public void onResponse(Call<DrinkObj> call, Response<DrinkObj> response) {
               DrinkObj ob = response.body();
                Drink [] arr = ob.drinkArray;
                Log.d("drink", arr[0].getStrDrink().toString());
            }
            @Override
            public void onFailure(Call<DrinkObj> call, Throwable t) {
            }
        });*/

/*        call.enqueue(new Callback<DrinkObj>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(!response.isSuccessful()){
                    Log.d("call back isSuccess", "Code: " + response.code());
                    return;
                }
                //JsonObject s = response.body();
                //JsonArray ja = s.getAsJsonArray();
               Log.d("drink", response.toString());
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
            }
        });*/

        //Call<List<Drink>> call = api.getDrinks();

/*        call.enqueue(new Callback<List<Drink>>() {
            @Override
            public void onResponse(Call<List<Drink>> call, Response<List<Drink>> response) {
                if(!response.isSuccessful()){
                    Log.d("call back isSuccess", "Code: " + response.code());
                    return;
                }
                List<Drink> drinks = response.body();
                for(Drink drink : drinks){
                    Log.d("drinks", drink.getStrDrink());
                }
            }
            @Override
            public void onFailure(Call<List<Drink>> call, Throwable t) {
                Log.d("call back onFail", t.getMessage());
            }
        });*/

    }
}