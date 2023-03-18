package com.example.csd230finalproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.csd230finalproject.databinding.FragmentMainBinding;

import java.util.ArrayList;


public class MainFragment extends Fragment {

    FragmentMainBinding binding;
    private static final String ARG_PARAM_DRINK = "ARG_PARAM_DRINK";

    private ArrayList<Drink> mDrinks;

    private DrinksAdapter adapter;
    private RecyclerView recycle;


    public MainFragment() {
        // Required empty public constructor
    }


    public static MainFragment newInstance(ArrayList<Drink> param1) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_DRINK, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDrinks =(ArrayList<Drink>) getArguments().getSerializable(ARG_PARAM_DRINK);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Cocktails");

        Log.d("Drink" , mDrinks.get(0).getStrDrink());

        adapter = new DrinksAdapter(getContext(), mDrinks);
        binding.rvDrinks.setLayoutManager(new LinearLayoutManager(getContext()));
         binding.rvDrinks.setAdapter(adapter);

 /*       binding.textViewName.setText(mProfile.name);
        binding.textViewEmail.setText(mProfile.email);
        binding.textViewId.setText(mProfile.id);
        binding.textViewDept.setText(mProfile.department);*/

    }
}