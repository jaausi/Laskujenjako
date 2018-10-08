package com.example.jannedesktop.laskujenjako;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Initialize text fields
        TextView textView = findViewById(R.id.textView3);
        textView.setText(message);

        // Initialize buttons
        Button buttonCopyOne = (Button) findViewById(R.id.button6);
        Button buttonCopyTwo = (Button) findViewById(R.id.button8);
        Button buttonStart = (Button) findViewById(R.id.button7);

        // Add listener to the copy button that copies the amount of person 1 groceries to the clipboard
        buttonCopyOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Jannen osuus kopioitu leikepöydälle"+" ("+MainActivity.dividendOne +"€)", Toast.LENGTH_SHORT).show();

                // Copy the information to the clipboard
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("summa",MainActivity.dividendOne);
                clipboard.setPrimaryClip(clip);
            }
        });

        // Add listener to the copy button that copies the amount of person 2 groceries to the clipboard
        buttonCopyTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Fannyn osuus kopioitu leikepöydälle"+" ("+MainActivity.dividendTwo +"€)", Toast.LENGTH_SHORT).show();

                // Copy the information to the clipboard
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("summa", MainActivity.dividendTwo);
                clipboard.setPrimaryClip(clip);
            }
        });

        // Add start Splitwise button listener
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "SplitWise käynnistetty", Toast.LENGTH_SHORT).show();
                String url = "https://secure.splitwise.com/api/v3.0/create_expense";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}
