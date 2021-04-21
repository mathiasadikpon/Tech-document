package com.example.foodieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemActivity extends AppCompatActivity {
    EditText uri;
    EditText name;
    EditText detail;
    public static final String EXTRA_REPLY =
            "com.example.android.foodieapp.extra.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        uri = findViewById(R.id.url_editText);
        name = findViewById(R.id.name_editText);
        detail = findViewById(R.id.ingrediant_editText);
    }

    public void sendFood(View view)
    {
        String uriString = uri.getText().toString().trim();
        String nameString = name.getText().toString().trim();
        String IngeString = name.getText().toString().trim();



        if(uriString.length()== 0 )
        {
            displayToast(getString(R.string.toast_link));
        }
        else if(nameString.length() == 0) {
            displayToast(getString(R.string.toast_name));
        }
        else if(IngeString.length() == 0)
            {
                displayToast(getString(R.string.toast_ingredient));
            }
        else {
            Intent replyIntent = new Intent();
            //replyIntent.putExtra(EXTRA_REPLY, uriString);
            String[] reply = {nameString, uriString, IngeString};
            replyIntent.putExtra(EXTRA_REPLY, reply);
            setResult(RESULT_OK, replyIntent);
            finish();
        }

    }

    private void displayToast(String message)
    {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }
}