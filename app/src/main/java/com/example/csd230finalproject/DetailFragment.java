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

import com.example.csd230finalproject.databinding.FragmentDetailBinding;


public class DetailFragment extends Fragment {

    FragmentDetailBinding binding;
    private static final String ARG_PARAM_ID = "ARG_PARAM_ID";


    private String mDrinkId;


    public DetailFragment() {
        // Required empty public constructor
    }



    public static DetailFragment newInstance(String drinkId) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_ID, drinkId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDrinkId = getArguments().getString(ARG_PARAM_ID);

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

        binding.cardTitle.setText(mDrinkId);
    }

}