package com.example.foodieapp;

import android.net.Uri;
import android.util.Log;

import java.util.LinkedList;

public class MealItem  extends LinkedList<String>
{
    // Member variables representing the title and information about the sport.
    private String title;
    private String info;
    private String link;
    private String ingredients;
    private final int imageResource;

    /**
     * Constructor for the food data model.
     *
     * @param title The name if the food.
     * @param info Information about the food.
     */
    public MealItem(String title, String info, String link, String ingredients, int imageResource) {
        this.title = title;
        this.info = info;
        this.link = link;
        this.ingredients = ingredients;
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public int getImageResource() {
        return imageResource;
    }
}