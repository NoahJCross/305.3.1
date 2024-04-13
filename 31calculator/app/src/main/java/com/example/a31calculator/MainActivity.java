package com.example.a31calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    // Declare UI elements
    private EditText val1; // EditText for first value
    private EditText val2; // EditText for second value
    private TextView res; // TextView to display result
    private Button calculateButton; // Button to perform calculation
    private Spinner typeSpinner; // Spinner to select operation type
    private String selectedType; // String to hold selected operation type

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        val1 = findViewById(R.id.val1);
        val2 = findViewById(R.id.val2);
        res = findViewById(R.id.res);
        typeSpinner = findViewById(R.id.typeSpinner);

        // Create ArrayAdapter for Spinner and set its dropdown layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);

        // Set listener for Spinner item selection
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View view, int position, long id) {
                selectedType = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing if no item is selected
            }
        });

        // Initialize calculateButton and set click listener
        calculateButton = findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if an operation type is selected before calculating
                if (!selectedType.isEmpty()) {
                    calculate(); // Perform calculation
                }
            }
        });
    }

    // Method to perform calculation based on selected operation type
    private void calculate() {
        // Get input values as strings
        String num1Str = val1.getText().toString();
        String num2Str = val2.getText().toString();

        // Check if either input is empty
        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
            return; // Exit method if inputs are missing
        }

        // Parse input strings to doubles
        double num1 = Double.parseDouble(num1Str);
        double num2 = Double.parseDouble(num2Str);
        double result;

        // Perform calculation based on selected operation type
        if (selectedType.equals("Addition")) {
            result = num1 + num2;
        } else {
            result = num1 - num2;
        }

        // Display result in TextView
        res.setText(String.valueOf(result));
    }
}
