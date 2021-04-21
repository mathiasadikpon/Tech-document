package com.example.scorecounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WinnerActivity extends AppCompatActivity
{
    private static final String KEY_COUNTER = "KEY_counter";
    private static final String TAGW = "WinnerActivity"; // MP3
    String [] arr = new String[3];
    TextView textViewWinner = null;

    //MP3 Add
    private String saveNumber = null;
    private EditText mCallFriendEditText;
    public static final int REQUEST_CALL_PHONE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        Intent intent = getIntent();

        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        // message = winnerTeamName +","+ points;
        String[] arr = message.split(",",2);
        String winnerTeamName = arr[0];
        String points = arr[1];
        textViewWinner = findViewById(R.id.winner);
        if((Integer.parseInt(points)) <= 1){
            textViewWinner.setText("The winner team is : " + winnerTeamName+"\nThey won by "+points + " pt");
        }
        else
            {
                textViewWinner.setText("The winner team is : " + winnerTeamName+"\nThey won by "+points+ " pts");
        }

        // MP3
        mCallFriendEditText = findViewById(R.id.call_friend_edittext);
       if(savedInstanceState != null)
        {
            saveNumber = savedInstanceState.getString(KEY_COUNTER, null);
            mCallFriendEditText.setText(saveNumber);
        }
    }

    // MP3
    // Method use to Call a friend to share the good news


    public void callFriendButton(View view)
    {
       makePhoneCall();
    }

    private void makePhoneCall()
    {
        String phoneNumber = mCallFriendEditText.getText().toString().trim();
        String dial = "tel:" + phoneNumber;
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(dial));
        if(phoneNumber.length()>0)
        {
            if(intent.resolveActivity(getPackageManager()) != null)
            {
                //startActivity(intent);

                if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {
                    Log.d(TAGW, "need to request permission to CALL_PHONE");
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);
                    //need to override onRequestPermissionsResult method for when the REQUEST_CALL_PHONE triggers the callback method
                }
                else
                {//have permission to CALL_PHONE so we can start the activity by passing in the intent
                    startActivity(intent);
                }
            }else
             {
                Log.d(TAGW, "Cannot place phone call");
                 displayToast("Cannot place phone call");
            }


            Log.d(TAGW, "end of dialNumber method");
        }
        else
        {
            displayToast("Enter Phone Number");
        }

    }


    public void sendMessage(View view)
    {
        send();
    }

    private void send()
    {
        String phoneNumber = mCallFriendEditText.getText().toString().trim();
        String message = textViewWinner.getText().toString();
        if(phoneNumber.length()>0)
        {
            Uri uri = Uri.parse("smsto:"+phoneNumber);
            Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
            intent.putExtra("sms_body", message);
            if(intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
        else
        {
            displayToast("Enter Phone Number");
        }
    }

    // Search for location
    public void openLocation(View view)
    {
        String loc = " Football near me ".trim();
        Uri addressUri = Uri.parse("geo: 0 , 0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        intent.setPackage("com.google.android.apps.maps");
        if(intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }
    }

    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        saveNumber = mCallFriendEditText.getText().toString();
        outState.putString(KEY_COUNTER, saveNumber);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAGW, "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAGW, "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAGW, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAGW, "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAGW, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAGW, "onDestroy");
    }
}