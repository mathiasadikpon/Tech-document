package com.example.foodieapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/***
 * The adapter class for the RecyclerView, contains the sports data.
 */
class MealItemAdapter extends RecyclerView.Adapter<MealItemAdapter.MealItemHolder>  {

    // Member variables.
    private ArrayList<MealItem> mFoodData;
    private Context mContext;

    /**
     * Constructor that passes in the sports data and the context.
     *
     * @param foodData ArrayList containing the sports data.
     * @param context Context of the application.
     */
    MealItemAdapter(Context context, ArrayList<MealItem> foodData) {
        this.mFoodData = foodData;
        this.mContext = context;
    }


    /**
     * Required method for creating the viewholder objects.
     *
     * @param parent The ViewGroup into which the new View will be added
     *               after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly created ViewHolder.
     */
    @Override
    public MealItemAdapter.MealItemHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new MealItemHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.list_item, parent, false));
    }

    /**
     * Required method that binds the data to the viewholder.
     *
     * @param holder The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(MealItemAdapter.MealItemHolder holder,
                                 int position) {
        // Get current food.
        MealItem currentFood = mFoodData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentFood);
    }


    /**
     * Required method for determining the size of the data set.
     *
     * @return Size of the data set.
     */
    @Override
    public int getItemCount() {
        return mFoodData.size();
    }


    /**
     * ViewHolder class that represents each row of data in the RecyclerView.
     */
    class MealItemHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener,View.OnLongClickListener {

        // Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView mFoodsImage;
        private TextView mDetail;


        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The rootview of the list_item.xml layout file.
         */
        MealItemHolder(View itemView) {
            super(itemView);

            // Initialize the views.(first page)
            mTitleText = itemView.findViewById(R.id.title);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mFoodsImage = itemView.findViewById(R.id.food_image);
            mDetail = itemView.findViewById(R.id.sub_Title_detail);
            // Set the OnClickListener to the entire view.
            itemView.setOnClickListener(this);

            // Set the OnLongClickListener to the entire view.
            itemView.setOnLongClickListener(this);
        }

        void bindTo(MealItem currentFood){
            // Populate the textviews with data.
            mTitleText.setText(currentFood.getTitle());
            mInfoText.setText(currentFood.getInfo());
            // Load the images into the ImageView using the Glide library.
            Glide.with(mContext).load(
                    currentFood.getImageResource()).into(mFoodsImage);

        }

        /**
         * Handle click to show DetailActivity.
         *
         * @param view View that is clicked.
         */
        @Override
        public void onClick(View view) {
            MealItem currentFood = mFoodData.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, DetailActivity.class);
            detailIntent.putExtra("title", currentFood.getTitle());
            detailIntent.putExtra("links", currentFood.getLink());
            detailIntent.putExtra("ingredient", currentFood.getIngredients());
            detailIntent.putExtra("image_resource",
                    currentFood.getImageResource());
            mContext.startActivity(detailIntent);
        }

        @Override
        public boolean onLongClick(View v)
        {
            mFoodData.remove(getAdapterPosition());
            //notifyDataSetChanged();
            notifyItemRemoved(getAdapterPosition());
            return true;

        }
    }
}