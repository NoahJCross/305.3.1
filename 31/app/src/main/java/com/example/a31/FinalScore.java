package com.example.a31;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class FinalScore extends AppCompatActivity {

    private TextView congrats;
    private TextView finalScore;
    private Button newQuiz;
    private Button finish;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_score);

        User user = User.getInstance();

        congrats = findViewById(R.id.congrats);
        finalScore = findViewById(R.id.finalScore);
        newQuiz = findViewById(R.id.newQuiz);
        finish = findViewById(R.id.finish);

        int score = getIntent().getIntExtra("score", 0);

        congrats.setText("Hello " + user.getName());
        finalScore.setText("Score: " + String.valueOf(score) + "/5");

        newQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinalScore.this, QuizActivity.class);
                startActivity(intent);
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });

    }
}
