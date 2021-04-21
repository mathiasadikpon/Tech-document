package com.example.tipcalculatormp4;

import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;


import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();
    private double billAmount = 0.0;
    private double percent = 0.15;
    private TextView amountTextView;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextView;

    //MP4
    private Spinner spinner;
    private ArrayAdapter<CharSequence> adapter;
    private EditText amountEditText;
    private TextView amountPersonTextView;
    private int numberOfPeople = 1;
    private String spinnerSelect;
    private double tip = 0.00;
    private double total = 0.00;
    private double amountPerPerson = 0.00;
    TextView firstTextView;

    private static final String TAG = "MainActivity";
    private static final String KEY_MESSAGE = "MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();

        amountTextView = findViewById(R.id.amount_textView);
        percentTextView = findViewById(R.id.percent_textView);
        tipTextView =  findViewById(R.id.tip_textView);
        totalTextView =  findViewById(R.id.total_textView);
        tipTextView.setText(currencyFormat.format(0));
        totalTextView.setText(currencyFormat.format(0));

        amountEditText = (EditText) findViewById(R.id.amount_editText);
        amountEditText.addTextChangedListener(amountEditTextWatcher);


        SeekBar percentSeekBar = (SeekBar) findViewById(R.id.percent_seekBar);
        percentSeekBar.setOnSeekBarChangeListener(seekBarListener);

        // MP4
        amountPersonTextView = findViewById(R.id.amount_per_person_textView);
        amountPersonTextView.setText(currencyFormat.format(0));
        firstTextView = findViewById(R.id.first_textView);

        spinner = findViewById(R.id.number_people_spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.people_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (spinner != null)
        {
            spinner.setOnItemSelectedListener(this);
            spinner.setAdapter(adapter);


        }
    }

    private final SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,boolean b) {
            percent = progress / 100.0;
            calculate();
        }
        public void onStartTrackingTouch(SeekBar seekBar) { }

        public void onStopTrackingTouch(SeekBar seekBar) { }
    };

    private void calculate() {
        Log.d("MainActivity", "inside calculate method");
        percentTextView.setText(percentFormat.format(percent));

        if((noBts && !tipBts && !totalBts) || (!noBts && !tipBts && !totalBts))
        {
            tip = billAmount * percent;
            total = billAmount + tip;
            amountPerPerson = total/numberOfPeople;
        }
        if(!noBts && tipBts && !totalBts)
        {
            tip = Math. ceil(billAmount * percent);
            total = billAmount + tip;
            amountPerPerson = total/numberOfPeople;
        }
        if(!noBts && !tipBts && totalBts)
        {
            tip = billAmount * percent;
            total = Math. ceil(billAmount + tip);
            amountPerPerson = total/numberOfPeople;
        }
        tipTextView.setText(currencyFormat.format(tip));
        totalTextView.setText(currencyFormat.format(total));
        amountPersonTextView.setText(currencyFormat.format(amountPerPerson));
    }



    private final TextWatcher amountEditTextWatcher = new TextWatcher()
    {

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.d("MainActivity", "inside onTextChanged method: charSequence= "+charSequence);
            try {
                billAmount = Double.parseDouble(charSequence.toString()) / 100.0;
                amountTextView.setText(currencyFormat.format(billAmount));
                Log.d("MainActivity", "Bill Amount = "+billAmount);
            }
            catch (NumberFormatException e) {
                amountTextView.setText("");
                billAmount = 0.0;
                Log.d("MainActivity", "Bill Amount = "+billAmount);
            }

            calculate();
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(CharSequence s, int i, int i1, int i2) { }
    };


    // MP4
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        spinnerSelect = parent.getItemAtPosition(position).toString();
        numberOfPeople = Integer.parseInt(spinnerSelect);
        calculate();
    }

    private void displayToast(String message)
    {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
    private boolean noBts =false;
    private boolean tipBts = false;
    private boolean totalBts = false;

    public void onRadioButtonClicked(View view)
    {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked.
        switch (view.getId()) {
            case R.id.no_button:
                if (checked) {
                    noBts = true;
                    tipBts = false;
                    totalBts = false;
                    calculate();
                    // The exact bill, tip, total will be used in calculations
                    //calcultion(bill, tip, total);
                    displayToast("The exact bill, tip, total will be used in calculations");
                }
                break;
            case R.id.tip_button:
                if (checked) {
                    noBts = false;
                    tipBts = true;
                    totalBts = false;
                    calculate();
                    displayToast("The tip will be rounded up before added to the bill to calculate the exact total");
                }
                break;
            case R.id.total_button:
                if (checked)
                {
                    noBts = false;
                    tipBts = false;
                    totalBts = true;
                    calculate();
                    displayToast("The bill and tip remain exact, but the total will be rounded up");
                }
                break;
            default:
                // Do nothing.
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(final Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_send:
                send();
                return true;
            case R.id.action_share:
                shareText();
                return true;
            case R.id.action_info:
                DialogFragment dialogFragment = new MyDialogFragment();//DialogFragment
                dialogFragment.show(getSupportFragmentManager(), "Mathias");

            default:
                // Do nothing
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareText()
    {
        String bill = "Bill: " + amountTextView.getText().toString();
        String tip = "Tip: " + tipTextView.getText().toString();
        String total = "Total: " + totalTextView.getText().toString();
        String person = "Bill Per Person: " + amountPersonTextView.getText().toString();
        String message = bill +"\n" + tip +"\n" +  total +"\n" +  person;
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share Text With")
                .setText(message)
                .startChooser();
    }
    private void send()
    {
        String bill = "Bill: " + amountTextView.getText().toString();
        String tip = "Tip: " + tipTextView.getText().toString();
        String total = "Total: " + totalTextView.getText().toString();
        String person = "Bill Per Person: " + amountPersonTextView.getText().toString();
        String message = bill +"\n" + tip +"\n" +  total +"\n" +  person;
        Uri uri = Uri.parse("smsto:"+ "13452837282");
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", message);
        if(intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }

    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String message = savedInstanceState.getString(KEY_MESSAGE,null);
        String []arr = new String[4];
        //String message = bill +";" + tip +";" +  total +";" +  person;
        arr = message.split(";", 4);
        String bill = arr[0];
        amountTextView.setText(bill);
        String tip = arr[1];
        tipTextView.setText(tip);
        String total = arr[2];
        totalTextView.setText(total);
        String person = arr[3];
        percentTextView.setText(person);
    }
    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        String bill = amountTextView.getText().toString();
        String tip = tipTextView.getText().toString();
        String total = totalTextView.getText().toString();
        String person = amountPersonTextView.getText().toString();
        String message = bill +";" + tip +";" +  total +";" +  person;
        outState.putString(KEY_MESSAGE, message);
    }

    @Override
    protected void onStart()
    {
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
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
