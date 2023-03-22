package com.example.csd230finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.csd230finalproject.databinding.ActivityMainBinding;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
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
    ArrayList<Drink> oneDrink;
    Drink mDrink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

       setSupportActionBar(binding.toolbar);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DrinksApi api = retrofit.create(DrinksApi.class);
        Call<DrinkList> call = api.getDrinkList();

        call.enqueue(new Callback<DrinkList>() {
            @Override
            public void onResponse(Call<DrinkList> call, Response<DrinkList> response) {

                //Log.d("drink", "on response");
                DrinkList myList = response.body();
                List<Drink> drinks = myList.getMyDrinks();
                alDrinks = new ArrayList<>(drinks.size());
                alDrinks.addAll(drinks);
                setData(alDrinks);

            }

            @Override
            public void onFailure(Call<DrinkList> call, Throwable t) {
                Log.d("Drink ", "In on failure");
            }
        });

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));

    }


    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String id = intent.getStringExtra("id");
            Log.d("Drink messenger", id);

            getDrinkFromApi(id);

        }
    };

    private void getDrinkFromApi(String id){
        //

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String url = "https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=" + id;
        DrinksApi api = retrofit.create(DrinksApi.class);
      // Call<DrinkList> call = api.getDrinkList();
        Call<DrinkList> call = api.getNextDrink(url);

        call.enqueue(new Callback<DrinkList>() {
            @Override
            public void onResponse(Call<DrinkList> call, Response<DrinkList> response) {

                Log.d("drink", "on response Detail");
                DrinkList myList = response.body();
                List<Drink> drinks = myList.getMyDrinks();
                oneDrink = new ArrayList<>(drinks.size());
                oneDrink.addAll(drinks);
               // setData(alDrinks);
                setDetailData(oneDrink);

            }

            @Override
            public void onFailure(Call<DrinkList> call, Throwable t) {
                if(t instanceof IOException)
                    Log.d("Drink on fail", "IoException");

                Log.d("Drink on fail" , t.getMessage());
                System.out.println("Network Error :: " + t.getLocalizedMessage());
                 t.printStackTrace();
            }
        });


    }
    private void setDetailData(ArrayList<Drink> data){
/*

        Drink dr = new Drink();
        dr.setIdDrink(Integer.parseInt(id));
*/
       Drink dr = data.get(0);
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragmentContainerView, DetailFragment.newInstance(dr))
                .commit();

/*        getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.rootView, DetailFragment.newInstance(dr))
                .commit();*/
    }
    private void setData(ArrayList<Drink> data){


        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainerView, MainFragment.newInstance(alDrinks))
                .commit();
 /*       getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView, MainFragment.newInstance(alDrinks))
                .commit();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

/*    @Override
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
        if(id == R.id.menuSpanish) {
            Toast.makeText(getApplicationContext(), "French", Toast.LENGTH_SHORT).show();
            return true;
        }

        Toast.makeText(this, "French", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }*/
}


