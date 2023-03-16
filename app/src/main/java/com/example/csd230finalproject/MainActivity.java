package com.example.csd230finalproject;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.csd230finalproject.databinding.ActivityMainBinding;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<Drink> alDrinks;
    private DrinksAdapter adapter;
    private RecyclerView recycle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

       // setSupportActionBar(binding.toolbar);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DrinksApi api = retrofit.create(DrinksApi.class);
        Call<DrinkList> call = api.getDrinkList();

        call.enqueue(new Callback<DrinkList>() {
            @Override
            public void onResponse(Call<DrinkList> call, Response<DrinkList> response) {
                DrinkList myList = response.body();
                List<Drink> drinks = myList.getMyDrinks();
                alDrinks = new ArrayList<>(drinks.size());
                alDrinks.addAll(drinks);
                setData(alDrinks);
            }

            @Override
            public void onFailure(Call<DrinkList> call, Throwable t) {

            }
        });
    }

    private void setData(ArrayList<Drink> data){
        Drink nextDrink = alDrinks.get(2);
        Log.d("outside Drink nextDrink", nextDrink.getStrDrink());

        adapter = new DrinksAdapter(this, alDrinks);
        binding.rvDrinks.setLayoutManager(new LinearLayoutManager(this));
        binding.rvDrinks.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menuEnglish) {
            Toast.makeText(getApplicationContext(), "English", Toast.LENGTH_SHORT).show();
            return true;
        }
        if(id == R.id.menuItalian) {
            Toast.makeText(getApplicationContext(), "Italian", Toast.LENGTH_SHORT).show();
            return true;
        }
        if(id == R.id.menuFrench) {
            Toast.makeText(getApplicationContext(), "French", Toast.LENGTH_SHORT).show();
            return true;
        }

        Toast.makeText(this, "French", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}


