package com.example.scorecounter;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    // Team 1
    private int scoreTeam1 = 0;
    private TextView displayScoreTeam1 = null;
    private Button nameTeam1 = null;
    private String  name1 = null;
    private EditText editText1 = null;
    private Button btn1 = null;

    // Team 2
    private int scoreTeam2 = 0;
    private TextView displayScoreTeam2 = null;
    private Button nameTeam2 = null;
    private String  name2 = null;
    private EditText editText2 = null;
    private Button btn2 = null;

    // Name of winner's Team
    private String winnerTeamName = null;
    private static final int WIN_NUMBER = 5;


    // Data to be sent to WinnerActivity
    private String message;
    public static String EXTRA_MESSAGE = "com.example.android.scorecounter.extra.MESSAGE";

    private static final String TAG = "MainActivity";
    private static final String KEY_MY_COUNTER = "KEY_Counter";
    private String saveString= null;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Team 1
        displayScoreTeam1 = findViewById(R.id.texViewTeam1);
        nameTeam1 = findViewById(R.id.buttonTeam1);
        editText1 = findViewById(R.id.editNameTeam1);
        btn1 = findViewById(R.id.button1);
        name1 = nameTeam1.getText().toString();


        // Team 2
        displayScoreTeam2 = findViewById(R.id.texViewTeam2);
        nameTeam2 = findViewById(R.id.buttonTeam2);
        editText2 = findViewById(R.id.editNameTeam2);
        btn2 = findViewById(R.id.button2);
        name2 = nameTeam2.getText().toString();

        //Save this information
        saveString = scoreTeam1 +"," + scoreTeam2 + ","+ name1 +","+ name2;


    }

    public void counterTeam1(View view)
    {
        scoreTeam1++;
        displayScoreTeam1.setText(" Downs: "+scoreTeam1);
        if(scoreTeam1 == WIN_NUMBER)
        {
            sendMessage(nameTeam1);
        }
    }
    public void counterTeam2(View view)
    {
        scoreTeam2++;
        displayScoreTeam2.setText(" Downs: "+scoreTeam2);
        if(scoreTeam2 == WIN_NUMBER)
        {
            sendMessage(nameTeam2);
        }
    }

    private void sendMessage(Button buttonView)
    {
        Intent intent = new Intent(this, WinnerActivity.class);
        winnerTeamName = buttonView.getText().toString();
        // Message
        int points = Math.abs(scoreTeam1 -scoreTeam2);
        message = winnerTeamName +","+ points;
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void team1Name(View view)
    {
        nameTeam1.setText(editText1.getText());
        editText1.setText("");
    }

    public void team2Name(View view)
    {
        nameTeam2.setText(editText2.getText());
        editText2.setText("");
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        saveString = savedInstanceState.getString(KEY_MY_COUNTER,null);
        String []arr = new String[4];
        //saveString = scoreTeam1 +"," + scoreTeam2 + ","+ name1 +","+ name2;
        arr = saveString.split(",", 4);
        scoreTeam1 = Integer.parseInt(arr[0]);
        scoreTeam2 = Integer.parseInt(arr[1]);
        name1 = arr[2];
        name2 = arr[3];
        if(displayScoreTeam1 != null)
        {
            displayScoreTeam1.setText(" Downs: "+scoreTeam1);
        }
        if(name1 != "TEAM 1")
        {
            nameTeam1.setText(name1);
        }
        if(displayScoreTeam2 != null)
        {
            displayScoreTeam2.setText(" Downs: "+scoreTeam2);
        }
        if(name2 != "TEAM 2")
        {
            nameTeam2.setText(name2);
        };
    }
    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        //Save this information
        name1 = nameTeam1.getText().toString();
        name2 = nameTeam2.getText().toString();
        saveString = scoreTeam1 +"," + scoreTeam2 + ","+ name1 +","+ name2;
        outState.putString(KEY_MY_COUNTER, saveString);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}