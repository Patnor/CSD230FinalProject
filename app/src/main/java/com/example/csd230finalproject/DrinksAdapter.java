package com.example.csd230finalproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.DrinksViewHolder> {
    public class DrinksViewHolder extends RecyclerView.ViewHolder {
        public ImageView drinkImage;
        public TextView textDrinkName;
        public TextView textId;

        public ImageView cardImage;
        public TextView cardTitle;

        public DrinksViewHolder(@NonNull View itemView) {
            super(itemView);
            drinkImage = itemView.findViewById(R.id.imageDrink);
            textDrinkName = itemView.findViewById(R.id.tvTitle);
            textId = itemView.findViewById(R.id.tvId);

            cardImage = itemView.findViewById(R.id.cardImage);
            cardTitle = itemView.findViewById(R.id.card_title);
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
        View drinkItemView = inflater.inflate(R.layout.card_item, parent, false);

        return new DrinksViewHolder(drinkItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinksAdapter.DrinksViewHolder holder, int position) {

/*        URL url = null;
        try {
            url = new URL("https://www.thecocktaildb.com/images/media/drink/qtv83q1596015790.jpg");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        // imageView.setImageBitmap(bmp);
        //Uri imageUri = Uri.parse("https://www.thecocktaildb.com/images/media/drink/qtv83q1596015790.jpg");
       // holder.drinkImage.setImageBitmap(bmp);
        //holder.drinkImage.setImageURI(imageUri);

    /*    Picasso.get().load(data.get(position).getStrDrinkThumb()).into(holder.drinkImage);
        //holder.drinkImage.setImageResource(R.drawable.drink_example_image);
        holder.textDrinkName.setText(data.get(position).getStrDrink());
        holder.textId.setText(String.valueOf(data.get(position).getIdDrink()));
*/
        Picasso.get().load(data.get(position).getStrDrinkThumb()).into(holder.cardImage);
        //holder.drinkImage.setImageResource(R.drawable.drink_example_image);
        holder.cardTitle.setText(data.get(position).getStrDrink());
       // holder.textId.setText(String.valueOf(data.get(position).getIdDrink()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
