package com.example.csd230finalproject;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.csd230finalproject.databinding.FragmentDetailBinding;
import com.squareup.picasso.Picasso;


public class DetailFragment extends Fragment {

    FragmentDetailBinding binding;
    private static final String ARG_PARAM_ID = "ARG_PARAM_ID";


    private String mDrinkId;
    private Drink mDrink;
    private int mId;

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
    }

}