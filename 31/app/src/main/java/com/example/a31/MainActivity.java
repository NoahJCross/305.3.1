package com.example.a31;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declare UI elements
    private TextView usersName; // TextView to display user's name
    private Button onNameSubmit; // Button to submit user's name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        usersName = findViewById(R.id.usersName); // Find TextView by id
        onNameSubmit = findViewById(R.id.onNameSubmit); // Find Button by id

        // Set click listener for name submission button
        onNameSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the user's name is empty
                if (usersName.getText().toString().isEmpty()) {
                    // Display error message if the name is empty
                    usersName.setError("Please enter your name");
                    return; // Exit the method if the name is empty
                }

                // Get singleton instance of User class and set the user's name
                User user = User.getInstance();
                user.setName(usersName.getText().toString());

                // Start QuizActivity
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });
    }
}
