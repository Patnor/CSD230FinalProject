package com.example.csd230finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.csd230finalproject.databinding.ActivityMainBinding;

import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity implements SearchFragment.SearchFragmentListener{
    ActivityMainBinding binding;
    ArrayList<Drink> alDrinks;
    ArrayList<Drink> oneDrink;


    Drink mDrink;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);

        drawerLayout = binding.rootView;
        navigationView = binding.navigationView;
        toolbar = binding.toolbar;
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.menu_open, R.string.menu_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

/*
        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.fragment_about);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);

        Button ok = findViewById(R.id.aboutButton);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
*/





        navigationView.setNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.nav_home:
                    getSupportFragmentManager().popBackStack();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.nav_search_name:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainerView, new SearchFragment())
                            .addToBackStack(null)
                            .commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.nav_search_ingred:
                    Log.d("MENU_DRAWER_TAG", "Search Ingredient Item is clicked");
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.nav_about:
                    dialog.show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.nav_share:

                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, R.string.app_name);
                    sendIntent.setType("text/plain");

                    Intent shareIntent = Intent.createChooser(sendIntent, "Share");
                    startActivity(shareIntent);




                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
            }
            return true;
        });



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DrinksApi api = retrofit.create(DrinksApi.class);
        Call<DrinkList> call = api.getDrinkList();

        call.enqueue(new Callback<DrinkList>() {
            @Override
            public void onResponse(@NonNull Call<DrinkList> call, @NonNull Response<DrinkList> response) {

                //Log.d("drink", "on response");
                DrinkList myList = response.body();
                assert myList != null;
                List<Drink> drinks = myList.getMyDrinks();
                alDrinks = new ArrayList<>(drinks.size());
                alDrinks.addAll(drinks);
                setData(alDrinks);

            }

            @Override
            public void onFailure(@NonNull Call<DrinkList> call, @NonNull Throwable t) {
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        int id = item.getItemId();
        if(id == R.id.app_bar_search) {
            Toast.makeText(getApplicationContext(), "Search", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getDrinkFromApi(String id) {
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
            public void onResponse(@NonNull Call<DrinkList> call, @NonNull Response<DrinkList> response) {

                Log.d("drink", "on response Detail");
                DrinkList myList = response.body();
                assert myList != null;
                List<Drink> drinks = myList.getMyDrinks();
                oneDrink = new ArrayList<>(drinks.size());
                oneDrink.addAll(drinks);
                // setData(alDrinks);
                setDetailData(oneDrink);

            }

            @Override
            public void onFailure(@NonNull Call<DrinkList> call, @NonNull Throwable t) {
                if (t instanceof IOException)
                    Log.d("Drink on fail", "IoException");

                Log.d("Drink on fail", t.getMessage());
                System.out.println("Network Error :: " + t.getLocalizedMessage());
                t.printStackTrace();
            }
        });


    }

    private void setDetailData(ArrayList<Drink> data) {
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

    private void setData(ArrayList<Drink> data) {


        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainerView, MainFragment.newInstance(alDrinks))
                .commit();
 /*       getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView, MainFragment.newInstance(alDrinks))
                .commit();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Name a cocktail");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                sendSearchWord(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public void sendSearchWord(String searchWord) {
       // getSupportFragmentManager().popBackStack();
        searchCocktail(searchWord);
    }

    private void searchCocktail(String word){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=" + word;
        DrinksApi api = retrofit.create(DrinksApi.class);
        // Call<DrinkList> call = api.getDrinkList();
        Call<DrinkList> call = api.getSearchDrink(url);

        call.enqueue(new Callback<DrinkList>() {
            @Override
            public void onResponse(@NonNull Call<DrinkList> call, @NonNull Response<DrinkList> response) {

                Log.d("drink", "on response Detail");
                DrinkList myList = response.body();
                assert myList != null;
                if(myList != null) {
                    List<Drink> drinks = myList.getMyDrinks();
                    if(drinks != null) {
                        oneDrink = new ArrayList<>(drinks.size());
                        oneDrink.addAll(drinks);
                        // setData(alDrinks);
                        setSearchData(oneDrink);
                    }
                    else{
                        Toast noToast = new Toast(getApplicationContext());
                        noToast.setGravity(Gravity.TOP,0,0);
                        noToast.setText("No cocktails found");
                        noToast.setDuration(Toast.LENGTH_SHORT);
                        noToast.show();
                        //Toast.makeText(getApplicationContext(), "No cocktails found", Toast.LENGTH_SHORT).setGravity(Gravity.TOP,50,50);
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<DrinkList> call, @NonNull Throwable t) {
                if (t instanceof IOException)
                    Log.d("Drink on fail", "IoException");

                Log.d("Drink on fail", t.getMessage());
                System.out.println("Network Error :: " + t.getLocalizedMessage());
                t.printStackTrace();
            }
        });
    }

    void setSearchData(ArrayList<Drink> data){
        //Drink dr = data.get(0);
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragmentContainerView,  MainFragment.newInstance(data))
                .commit();


    }

 /*   @Override
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


