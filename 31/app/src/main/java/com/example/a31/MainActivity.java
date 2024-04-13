package com.example.a31;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView usersName;
    private Button onNameSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usersName = findViewById(R.id.usersName);
        onNameSubmit = findViewById(R.id.onNameSubmit);
        onNameSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(usersName.getText().toString().isEmpty()){
                    usersName.setError("Please enter your name");
                    return;
                }
                User user = User.getInstance();
                user.setName(usersName.getText().toString());
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });

    }
}