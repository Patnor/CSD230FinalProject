package com.example.csd230finalproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.DrinksViewHolder> {

    String[] strArrayWords;
    Random rand = new Random();

    public class DrinksViewHolder extends RecyclerView.ViewHolder {
        public ImageView cardImage;
        public TextView cardTitle;
        public TextView cardId;
        public Button cardTryIt;

        public DrinksViewHolder(@NonNull View itemView) {
            super(itemView);

            cardImage = itemView.findViewById(R.id.cardImage);
            cardTitle = itemView.findViewById(R.id.card_title);
            cardId = itemView.findViewById(R.id.card_id);
            cardTryIt = itemView.findViewById(R.id.card_button_try_it);

            strArrayWords = new String[5];
            strArrayWords[0] = "Try It!";
            strArrayWords[1] = "Show Me!";
            strArrayWords[2] = "Make It!";
            strArrayWords[3] = "Do It!";
            strArrayWords[4] = "Yum!";
        }
    }

    private LayoutInflater inflater;

    public ArrayList<Drink> data;
    private String drinkId;

    public DrinksAdapter(Context context, ArrayList<Drink> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public DrinksAdapter.DrinksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View drinkItemView = inflater.inflate(R.layout.card_item, parent, false);

        return new DrinksViewHolder(drinkItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinksAdapter.DrinksViewHolder holder, int position) {

        Picasso.get().load(data.get(position).getStrDrinkThumb()).into(holder.cardImage);
        holder.cardTitle.setText(data.get(position).getStrDrink());
        holder.cardId.setText((String.valueOf(data.get(position).getIdDrink())));

        int pos = position;
        int num = rand.nextInt(5);
        holder.cardTryIt.setText(strArrayWords[num]);

        holder.cardTryIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drinkId = String.valueOf((data.get(pos).getIdDrink()));

                Intent intent = new Intent("custom-message");
                intent.putExtra("id",drinkId);
                LocalBroadcastManager.getInstance(v.getContext()).sendBroadcast(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public String getDrinkId(){
        return drinkId;
    }
}
