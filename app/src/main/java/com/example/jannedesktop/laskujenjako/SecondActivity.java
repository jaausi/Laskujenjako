package com.example.jannedesktop.laskujenjako;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SecondActivity extends AppCompatActivity {

    public double ownGroceries = 0.0; // Variable for the total combined amount of person 1 and person 2 groceries so we can test that this value wont exceed the value of groceries total.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView2);
        textView.setText("Lasku yhteensä: "+message+" €");

        // Initialize buttons
        Button buttonJanne = (Button) findViewById(R.id.button3);
        Button buttonFanny = (Button) findViewById(R.id.button2);
        Button buttonSeuraava = (Button) findViewById(R.id.button4);

        // Initialize text fields
        final EditText editText = findViewById(R.id.editText2);
        final TextView textView5 = findViewById(R.id.textView5);

        // Add listener to the person 1 groceries button
        buttonJanne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String teksti = editText.getText().toString();
                // Has the user given value in the field
                if(teksti.length()!=0){
                    // Test if the own groceries exceeds the amount of total groceries
                    if(Double.parseDouble(teksti) + ownGroceries < MainActivity.doubleTotalSum){
                        ownGroceries = ownGroceries + Double.parseDouble(teksti);
                        MainActivity.groceriesOne.add(Double.parseDouble(teksti));
                        textView5.setText("Edellinen syötetty luku: "+teksti+" (Jannen)");
                        editText.setText("");
                    }
                    else{
                        Toast.makeText(v.getContext(), "Virheellinen arvo syötetty. Kertyneet omat ostokset ylittävät kokonaisostosten hinnan.", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(v.getContext(), "Syötä arvo kenttään", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Add listener to the person 2 groceries button
        buttonFanny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String teksti = editText.getText().toString();
                // Has the user given value in the field
                if(teksti.length()!=0){
                    // Test if the own groceries exceeds the amount of total groceries
                    if(Double.parseDouble(teksti) + ownGroceries < MainActivity.doubleTotalSum){
                        ownGroceries = ownGroceries + Double.parseDouble(teksti);
                        MainActivity.groceriesTwo.add(Double.parseDouble(teksti));
                        textView5.setText("Edellinen syötetty luku: "+teksti+" (Fannyn)");
                        editText.setText("");
                    }
                    else{
                        Toast.makeText(v.getContext(), "Virheellinen arvo syötetty. Kertyneet omat ostokset ylittävät kokonaisostosten hinnan.", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(v.getContext(), "Syötä arvo kenttään", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Add listener for the button to the next intent
        buttonSeuraava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Seuraava nappia painettu", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);

                String message = makeMessage();
                intent.putExtra(MainActivity.EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });
    }

    /**
     * Makes the message seen in the last screen
     * @return message of the last page
     */
    private String makeMessage(){
        String viesti = "";
        viesti = "Yhteiset ostokset: "+MainActivity.totalSum +" €"+System.getProperty("line.separator");
        double personOneSum =0;
        for(double luku:MainActivity.groceriesOne){
            personOneSum=personOneSum+luku;
        }
        double roundedJ = round(personOneSum,2);
        double personTwoSum=0;
        for(double luku:MainActivity.groceriesTwo){
            personTwoSum=personTwoSum+luku;
        }
        double roundedF = round(personTwoSum, 2);
        MainActivity.dividendTwo = String.valueOf(osuus(personTwoSum,personOneSum));
        MainActivity.dividendOne = String.valueOf(osuus(personOneSum,personTwoSum));
        viesti=viesti+"Jannen omat ostokset: "+String.valueOf(roundedJ)+" €"+System.getProperty("line.separator");
        viesti=viesti+"Fannyn omat ostokset: "+String.valueOf(roundedF)+" €"+System.getProperty("line.separator");
        viesti=viesti+"Jannen osuus (yhteiset mukaanlukien): "+MainActivity.dividendOne +" €"+System.getProperty("line.separator");
        viesti=viesti+"Fannyn osuus (yhteiset mukaanlukien): "+MainActivity.dividendTwo +" €"+System.getProperty("line.separator");
        return viesti;
    }


    /**
     * Method that returns dividend including the shared groceries and the own groceries.
     * @param personOne Own groceries of a person 1
     * @param personTwo Own groceries of a person 2
     * @return
     */
    public double osuus(double personOne, double personTwo){
        double shared = MainActivity.doubleTotalSum -(personOne+personTwo);
        shared=(shared/2);
        double personOneDiv = shared+personOne;
        return round(personOneDiv,2);
    }

    /**
     * Method that rounds a double variable to the wanted accuracy
     * @param value Value that will be rounded
     * @param places Number of decimals wanted
     * @return returns rounded double
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
