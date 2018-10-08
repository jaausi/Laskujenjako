package com.example.jannedesktop.laskujenjako;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * I ran into this problem when I went to the grocery store with my girlfriend we bought some stuff that we share and some stuff just to ourselves.
 * After the bill was handled and we wanted to share it in the Splitwise, we always have to first figure out with calculator how much one person is
 * owning to another because part of the groceries were shared and part were our own. So this application is made just to ease up the struggle of
 * calculating the expenses.
 */
public class MainActivity extends AppCompatActivity {

    public static String totalSum; // Total sum of your groceries
    public static double doubleTotalSum; // Total sum of your groceries as a double
    public static ArrayList<Double> groceriesOne; // Arraylist, that includes the groceries of a person 1
    public static ArrayList<Double> groceriesTwo; // Arraylist, that includes the groceries of a person 2
    public static String dividendOne; // String that includes share of a person 1, including half of the total sum and own groceries
    public static String dividendTwo; // String that includes share of a person 2, including half of the total sum and own groceries
    public static final String EXTRA_MESSAGE = "com.example.jannedesktop.laskujenjako.MESSAGE"; // Variable that moves the information from one intent to another.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Opens the Main activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the variables
        initializeVars();

        // Initialize editText field
        final EditText editText = findViewById(R.id.editText);

        // Button listener for the button that lets the user to proceed to the next intent
        final Button nextButton = findViewById(R.id.button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                totalSum = editText.getText().toString();
                // Has the user given value for the total amount of groceries
                if(totalSum.length()!=0){
                    doubleTotalSum = Double.parseDouble(totalSum);
                    doubleTotalSum = SecondActivity.round(doubleTotalSum,2);
                    totalSum = String.valueOf(doubleTotalSum);
                    // Initialize new activity
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    String message = totalSum;
                    intent.putExtra(EXTRA_MESSAGE, message);
                    startActivity(intent);
                }
                // If there is no value given for the groceries tell the user to give that value
                else{
                    Toast.makeText(v.getContext(), "Syötä arvo kenttään.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // Button listener for the button that initializes the variables
        final Button buttonInitialize = findViewById(R.id.button5);
        buttonInitialize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Muuttujat alustettu.", Toast.LENGTH_SHORT).show();
                initializeVars();
                editText.setText("");
            }
        });

    }

    /**
     * Method that initializes the variables
     */
    private void initializeVars(){
        totalSum ="";
        doubleTotalSum =0;
        dividendOne ="";
        dividendTwo ="";
        groceriesOne = new ArrayList<>();
        groceriesTwo = new ArrayList<>();
    }
}
