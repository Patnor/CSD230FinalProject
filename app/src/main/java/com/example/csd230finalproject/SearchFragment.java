package com.example.csd230finalproject;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.csd230finalproject.databinding.FragmentSearchBinding;

public class SearchFragment extends Fragment {

    FragmentSearchBinding binding;

    public SearchFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSubmit.setOnClickListener(v -> {
            String word = binding.editTextSearch.getText().toString();
            mSearchListener.sendSearchWord(word);

        });
    }

    SearchFragmentListener mSearchListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        mSearchListener = (SearchFragmentListener) context;
    }

    interface SearchFragmentListener{
        void sendSearchWord(String searchWord);
    }

}