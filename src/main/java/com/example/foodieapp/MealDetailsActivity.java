package com.example.foodieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MealDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);
        // Initialize the views.
        TextView foodTitle = findViewById(R.id.random_title_Detail);
        ImageView foodImage = findViewById(R.id.random_food_image_detail);
        TextView foodLink = findViewById(R.id.random_food_link_detail);
        TextView foodIngredient = findViewById(R.id.random_sub_Title_detail);


        // Set the text from the Intent extra.
        foodTitle.setText(getIntent().getStringExtra("title"));
        foodIngredient.setText(getIntent().getStringExtra("ingredient"));
        foodLink.setText(getIntent().getStringExtra("links"));
        // Load the image using the Glide library and the Intent extra.
        Glide.with(this)
                .load(getIntent()
                        .getIntExtra("image_resource",0))
                .into(foodImage);
    }
    @Override
    public boolean onCreateOptionsMenu(final Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}