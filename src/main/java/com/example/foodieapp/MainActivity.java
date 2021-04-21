package com.example.foodieapp;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.UriMatcher;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***
 * Main Activity for the Material Me app, a mock sports news application.
 */
public class MainActivity extends AppCompatActivity {


    // Member variables.
    private RecyclerView mRecyclerView;
    private ArrayList<MealItem> mFoodData;
    private MealItemAdapter mAdapter;


    public static final int TEXT_REQUEST = 1;

    //MP6
    private CustomReceiver mReceiver = new CustomReceiver();

    // String constant that defines the custom broadcast Action.
    private static final String I_AM_HOME =
            "com.example.basic.I_AM_HOME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MP6
        // Define the IntentFilter.
        IntentFilter filter = new IntentFilter();
        // Add system broadcast actions sent by the system
        //filter.addAction(Intent.ACTION_POWER_CONNECTED);
        //filter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        // Register the receiver using the activity context, passing in the
        // IntentFilter.
        this.registerReceiver(mReceiver, filter);

        // Register the receiver to receive custom broadcast.
        LocalBroadcastManager.getInstance(this).registerReceiver
                (mReceiver, new IntentFilter(I_AM_HOME));


        //MP5
        // Initialize the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerView);

        // Get the appropriate column count.
        int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);

        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new GridLayoutManager(
                this, gridColumnCount));

        // Initialize the ArrayList that will contain the data.
        mFoodData = new ArrayList<>();

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new MealItemAdapter(this, mFoodData);
        mRecyclerView.setAdapter(mAdapter);

        // Get the data.
        initializeData();

        // If there is more than one column, disable swipe to dismiss
        int swipeDirs;
        if(gridColumnCount > 1){
            swipeDirs = 0;
        } else {
            swipeDirs = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        }

        // Helper class for creating swipe to dismiss and drag and drop
        // functionality
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper
                .SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                        ItemTouchHelper.DOWN | ItemTouchHelper.UP,
                swipeDirs) {
            /**
             * Defines the drag and drop functionality.
             *
             * @param recyclerView The RecyclerView that contains the list items.
             * @param viewHolder The SportsViewHolder that is being moved.
             * @param target The SportsViewHolder that you are switching the
             *               original one with.
             * @return returns true if the item was moved, false otherwise
             */
            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                // Get the from and to positions.
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();

                // Swap the items and notify the adapter.
                Collections.swap(mFoodData, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            /**
             * Defines the swipe to dismiss functionality.
             *
             * @param viewHolder The viewholder being swiped.
             * @param direction The direction it is swiped in.
             */
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                // Remove the item from the dataset.
                mFoodData.remove(viewHolder.getAdapterPosition());
                // Notify the adapter.
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        // Attach the helper to the RecyclerView.
        helper.attachToRecyclerView(mRecyclerView);
    }

    /**
     * Initialize the sports data from resources.
     */
    private void initializeData() {
        // Get the resources from the XML file.
        String[] foodList = getResources()
                .getStringArray(R.array.food_titles);
        String[] foodInfo = getResources()
                .getStringArray(R.array.food_info);

        String [] foodLink = getResources().getStringArray(R.array.food_links);
        String [] foodIng = getResources().getStringArray(R.array.food_ingredient);

        TypedArray foodImageResources =
                getResources().obtainTypedArray(R.array.food_images);

        // Clear the existing data (to avoid duplication).
        mFoodData.clear();

        // Create the ArrayList of Sports objects with the titles and
        // information about each MealItem.
        for (int i = 0; i < foodList.length; i++) {
            mFoodData.add(new MealItem(foodList[i], foodInfo[i], foodLink[i],foodIng[i],
                    foodImageResources.getResourceId(i, 0)));
        }

        // Recycle the typed array.
        foodImageResources.recycle();

        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();
    }

    /**
     * onClick method for th FAB that resets the data.
     *
     * @param view The button view that was clicked.
     */
    public void addFood(View view)
    {
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }
    @Override
    public boolean onCreateOptionsMenu(final Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Test for the right intent reply.

        if (requestCode == TEXT_REQUEST) {
            // Test to make sure the intent reply result was good.
            if (resultCode == RESULT_OK) {
                String [] arr = data.getStringArrayExtra(AddItemActivity.EXTRA_REPLY);
                String tittleAddName = arr[0];
                String uriString = arr[1];
                String ingreDetail = arr[2];
                ImageView im = findViewById(R.id.food_image) ;
                int i = im.getId();
                //im.setImageURI(Uri.parse(uriString));
                //Glide.with(getApplicationContext()).load(Uri.parse(uriString)).asBitmap().into(im);

                MealItem newMeal = new MealItem(tittleAddName, getString(R.string.food_info_placeholder)+" "+ tittleAddName, uriString, ingreDetail,i);
                mFoodData.add(newMeal);
                int mFoodSize = mFoodData.size();
                mAdapter.notifyDataSetChanged();
                mRecyclerView.smoothScrollToPosition(mFoodSize);
            }
        }
    }

    public void sendCustomBroadcast(View view) {
        int numFood = mFoodData.size();
        int num = (int)(Math.random()*numFood);
        MealItem currentFood = mFoodData.get(num);
        Intent customBroadcastIntent = new Intent(I_AM_HOME);
        customBroadcastIntent.putExtra("title", currentFood.getTitle());
        LocalBroadcastManager.getInstance(this)
                .sendBroadcast(customBroadcastIntent);

        Intent detailIntent = new Intent(getApplicationContext(), MealDetailsActivity.class);
        detailIntent.putExtra("title", currentFood.getTitle());
        detailIntent.putExtra("links", currentFood.getLink());
        detailIntent.putExtra("ingredient", currentFood.getIngredients());
        detailIntent.putExtra("image_resource",
                currentFood.getImageResource());
        startActivity(detailIntent);
    }

    /**
     * Unregisters the broadcast receivers when the app is destroyed.
     */
    @Override
    protected void onDestroy() {
        // Unregister the receivers.
        this.unregisterReceiver(mReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
        super.onDestroy();
    }
}