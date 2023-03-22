package com.example.csd230finalproject;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.csd230finalproject.databinding.FragmentDetailBinding;
import com.squareup.picasso.Picasso;


public class DetailFragment extends Fragment {

    FragmentDetailBinding binding;
    private static final String ARG_PARAM_ID = "ARG_PARAM_ID";


    private String mDrinkId;
    private Drink mDrink;
    private int mId;


    Boolean mIT = false;
    Boolean mEN = false;
    Boolean mDE = false;
    Boolean mFR = false;
    Boolean mES = false;

    public DetailFragment() {
        // Required empty public constructor
    }



    public static DetailFragment newInstance(Drink drink) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM_ID, drinkId);
        args.putSerializable(ARG_PARAM_ID, drink);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (getArguments() != null) {
            mDrink = (Drink) getArguments().getSerializable(ARG_PARAM_ID);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false );
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Cocktail Details");


        // load photo and title
        Picasso.get().load(mDrink.getStrDrinkThumb()).into(binding.cardImage);
        binding.cardTitle.setText(String.valueOf(mDrink.getStrDrink()));


        // load directions
        TextView tv_directions = binding.cardViewDirections.findViewById(R.id.tv_directions_body);
        tv_directions.setText(mDrink.getStrInstructions());

        // load ingredients

        TextView tvm1 =  binding.cardViewIngredient.findViewById(R.id.tv_measure1);
        tvm1.setText(mDrink.getStrMeasure1());
        TextView tvI1 = binding.cardViewIngredient.findViewById(R.id.tv_ingredient1);
        tvI1.setText(mDrink.getStrIngredient1());

        tvm1 =  binding.cardViewIngredient.findViewById(R.id.tv_measure2);
        tvm1.setText(mDrink.getStrMeasure2());
        tvI1 = binding.cardViewIngredient.findViewById(R.id.tv_ingredient2);
        tvI1.setText(mDrink.getStrIngredient2());

        tvm1 =  binding.cardViewIngredient.findViewById(R.id.tv_measure3);
        tvm1.setText(mDrink.getStrMeasure3());
        tvI1 = binding.cardViewIngredient.findViewById(R.id.tv_ingredient3);
        tvI1.setText(mDrink.getStrIngredient3());

        tvm1 =  binding.cardViewIngredient.findViewById(R.id.tv_measure4);
        tvm1.setText(mDrink.getStrMeasure4());
        tvI1 = binding.cardViewIngredient.findViewById(R.id.tv_ingredient4);
        tvI1.setText(mDrink.getStrIngredient4());

        tvm1 =  binding.cardViewIngredient.findViewById(R.id.tv_measure5);
        tvm1.setText(mDrink.getStrMeasure5());
        tvI1 = binding.cardViewIngredient.findViewById(R.id.tv_ingredient5);
        tvI1.setText(mDrink.getStrIngredient5());

        tvm1 =  binding.cardViewIngredient.findViewById(R.id.tv_measure6);
        tvm1.setText(mDrink.getStrMeasure6());
        tvI1 = binding.cardViewIngredient.findViewById(R.id.tv_ingredient6);
        tvI1.setText(mDrink.getStrIngredient6());

        tvm1 =  binding.cardViewIngredient.findViewById(R.id.tv_measure7);
        tvm1.setText(mDrink.getStrMeasure7());
        tvI1 = binding.cardViewIngredient.findViewById(R.id.tv_ingredient7);
        tvI1.setText(mDrink.getStrIngredient7());

        tvm1 =  binding.cardViewIngredient.findViewById(R.id.tv_measure8);
        tvm1.setText(mDrink.getStrMeasure8());
        tvI1 = binding.cardViewIngredient.findViewById(R.id.tv_ingredient8);
        tvI1.setText(mDrink.getStrIngredient8());

        Log.d("Drink" , mDrink.getStrInstructionsIT());

        if(mDrink.getStrInstructionsIT() != null){
            mIT = true;
        }
        if(mDrink.getStrInstructionsES() != null){
           mES = true;
        }
        if(mDrink.getStrInstructionsDE() != null){
            mDE = true;
        }
        if(mDrink.getStrInstructionsFR() != null){
            mFR = true;
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.menuEnglish).setVisible(true);
        menu.findItem(R.id.menuFrench).setVisible(false);
        menu.findItem(R.id.menuItalian).setVisible(false);
        menu.findItem(R.id.menuGerman).setVisible(false);
        menu.findItem(R.id.menuSpanish).setVisible(false);

        if(mIT)
            menu.findItem(R.id.menuItalian).setVisible(true);
        if(mES) {
            menu.findItem(R.id.menuSpanish).setVisible(true);
        }
        if(mFR)
            menu.findItem(R.id.menuFrench).setVisible(true);
        if(mDE)
            menu.findItem(R.id.menuGerman).setVisible(true);


        //mMenuIT = menu.findItem(R.id.menuItalian);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menuEnglish) {
            TextView tv_directions = binding.cardViewDirections.findViewById(R.id.tv_directions_body);
            tv_directions.setText(mDrink.getStrInstructions());
            return true;
        }
        if(id == R.id.menuItalian) {
            // load directions
            TextView tv_directions = binding.cardViewDirections.findViewById(R.id.tv_directions_body);
            tv_directions.setText(mDrink.getStrInstructionsIT());
           // Toast.makeText(getContext(), "Italian", Toast.LENGTH_SHORT).show();
            return true;
        }
        if(id == R.id.menuFrench) {
            TextView tv_directions = binding.cardViewDirections.findViewById(R.id.tv_directions_body);
            tv_directions.setText(mDrink.getStrInstructionsFR());
            return true;
        }
        if(id == R.id.menuSpanish) {
            TextView tv_directions = binding.cardViewDirections.findViewById(R.id.tv_directions_body);
            tv_directions.setText(mDrink.getStrInstructionsES());
            return true;
        }
        if(id == R.id.menuGerman) {
            TextView tv_directions = binding.cardViewDirections.findViewById(R.id.tv_directions_body);
            tv_directions.setText(mDrink.getStrInstructionsDE());
            return true;
        }

       // Toast.makeText(this, "French", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}