package com.example.foodieapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Broadcast Receiver implementation that delivers a custom Toast
 * message when it receives any of the registered broadcasts.
 */
public class CustomReceiver extends BroadcastReceiver {

    // String constant that defines the custom broadcast Action.
    private static final String I_AM_HOME =
            "com.example.basic.I_AM_HOME";

    /**
     * This callback method gets called when the Broadcast Receiver receives a
     * broadcast that it is registered for.
     *
     * @param context The context in which broadcast receiver is running.
     * @param intent The broadcast is delivered in the form of an intent which
     *               contains the broadcast action.
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();


        if (intentAction != null) {
            String toastMessage = context.getString(R.string.unknown_action);
            if((intentAction == I_AM_HOME))
            {
                String title = intent.getStringExtra("title");
                toastMessage = context.getString(R.string.happy_cooking) +" "+ title;
            }
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
        }
    }
}