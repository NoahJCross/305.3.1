package com.example.a31;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class FinalScore extends AppCompatActivity {

    // Declare UI elements
    private TextView congrats; // TextView to display congratulatory message
    private TextView finalScore; // TextView to display final score
    private Button newQuiz; // Button to start a new quiz
    private Button finish; // Button to finish the quiz and exit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_score);

        // Get user instance from singleton class
        User user = User.getInstance();

        // Initialize UI elements
        congrats = findViewById(R.id.congrats);
        finalScore = findViewById(R.id.finalScore);
        newQuiz = findViewById(R.id.newQuiz);
        finish = findViewById(R.id.finish);

        // Get score passed from the previous activity
        int score = getIntent().getIntExtra("score", 0);

        // Display congratulatory message with user's name
        congrats.setText("Hello " + user.getName());

        // Display final score
        finalScore.setText("Score: " + String.valueOf(score) + "/5");

        // Set click listener for starting a new quiz
        newQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start QuizActivity
                Intent intent = new Intent(FinalScore.this, QuizActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for finishing the quiz and exiting
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish all activities in the task associated with this activity
                finishAffinity();
            }
        });

    }
}
