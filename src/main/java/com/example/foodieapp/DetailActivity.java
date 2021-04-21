package com.example.foodieapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/***
 * Detail Activity that is launched when a list item is clicked.
 * It shows more info on the sport.
 */
public class DetailActivity extends AppCompatActivity {

    /**
     * Initializes the activity, filling in the data from the Intent.
     *
     * @param savedInstanceState Contains information about the saved state
     *                           of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Initialize the views.
        TextView foodTitle = findViewById(R.id.titleDetail);
        ImageView foodImage = findViewById(R.id.food_image_detail);
        TextView foodLink = findViewById(R.id.food_link_detail);
        TextView foodIngredient = findViewById(R.id.sub_Title_detail);

        // Set the text from the Intent extra.
        foodTitle.setText(getIntent().getStringExtra("title"));

        foodLink.setText(getIntent().getStringExtra("links"));
        foodIngredient.setText(getIntent().getStringExtra("ingredient"));
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