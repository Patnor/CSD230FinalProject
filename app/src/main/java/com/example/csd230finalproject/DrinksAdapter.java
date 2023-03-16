package com.example.csd230finalproject;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.DrinksViewHolder> {
    public class DrinksViewHolder extends RecyclerView.ViewHolder {
        public ImageView drinkImage;
        public TextView textDrinkName;
        public TextView textId;

        public DrinksViewHolder(@NonNull View itemView) {
            super(itemView);
            drinkImage = itemView.findViewById(R.id.imageDrink);
            textDrinkName = itemView.findViewById(R.id.tvTitle);
            textId = itemView.findViewById(R.id.tvId);
        }
    }

    private LayoutInflater inflater;

    public ArrayList<Drink> data;

    public DrinksAdapter(Context context, ArrayList<Drink> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public DrinksAdapter.DrinksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View drinkItemView = inflater.inflate(R.layout.layout_drink, parent, false);

        return new DrinksViewHolder(drinkItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinksAdapter.DrinksViewHolder holder, int position) {

        //Uri imageUri = Uri.parse(data.get(position).getStrDrinkThumb());
        holder.drinkImage.setImageResource(R.drawable.drink_example_image);
        holder.textDrinkName.setText(data.get(position).getStrDrink());
        holder.textId.setText(String.valueOf(data.get(position).getIdDrink()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
