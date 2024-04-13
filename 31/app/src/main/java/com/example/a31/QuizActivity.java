package com.example.a31;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private String userName;
    private Question[] questions = {
            new Question("What is the capital of France?", new String[]{"Paris", "London", "Berlin", "Victoria"}, 0),
            new Question("Which planet is known as the Red Planet?", new String[]{"Mars", "Jupiter", "Venus", "Moon"}, 1),
            new Question("Who wrote 'Romeo and Juliet'?", new String[]{"William Shakespeare", "Jane Austen", "Charles Dickens", "Michael Jackson"}, 0),
            new Question("What is the capital of Australia?", new String[]{"Sydney", "Melbourne", "Canberra", "Northern Territory"}, 2),
            new Question("Which is the largest ocean on Earth?", new String[]{"Pacific Ocean", "Atlantic Ocean", "Indian Ocean", "Southern Ocean"}, 0),
    };
    private boolean shouldSubmit = true;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private TextView userGreeting;
    private ProgressBar progressBar;
    private TextView progressText;
    private TextView questionTitle;
    private RadioButton answer1;
    private RadioButton answer2;
    private RadioButton answer3;
    private RadioButton answer4;
    private Button submitAnswer;
    private TextView currentScore;
    private int finalAnswer = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);
        User user = User.getInstance();

        userGreeting = findViewById(R.id.userGreeting);
        userGreeting.setText("Hello " + user.getName());


        progressBar = findViewById(R.id.progressBar);
        progressText = findViewById(R.id.progressText);
        questionTitle = findViewById(R.id.questionTitle);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        submitAnswer = findViewById(R.id.submitAnswer);
        currentScore = findViewById(R.id.currentScore);

        updateQuestion();
        submitAnswer.setOnClickListener(this);
        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);
        answer4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.submitAnswer) {
            if (shouldSubmit) {
                if (finalAnswer == -1) {
                    Toast.makeText(this, "Select an Answer", Toast.LENGTH_SHORT).show();
                } else {
                    checkAnswer();
                }
            } else {
                currentQuestionIndex++;
                updateQuestion();
            }
        } else {
            onAnswerClick(v);
        }
    }

    private void updateView() {
        progressBar.setProgress((currentQuestionIndex + 1) * 20);
        currentScore.setText("Score: " + String.valueOf(score) + "/5");
        RadioButton answer = getAnswer(finalAnswer);
        answer.setChecked(false);
        submitAnswer.setText("NEXT");
        shouldSubmit = false;
        answer1.setEnabled(false);
        answer2.setEnabled(false);
        answer3.setEnabled(false);
        answer4.setEnabled(false);
    }

    private void onAnswerClick(View view) {
        if (finalAnswer != -1) {
            RadioButton prevFinal = getAnswer(finalAnswer);
            prevFinal.setBackgroundResource(0);
        }
        finalAnswer = getAnswerIndex(view.getId());
        RadioButton currentFinal = getAnswer(finalAnswer);
        currentFinal.setBackgroundResource(R.drawable.selected_background);
    }

    private int getAnswerIndex(int id) {
        if (id == R.id.answer1) {
            return 0;
        } else if (id == R.id.answer2) {
            return 1;
        } else if (id == R.id.answer3) {
            return 2;
        } else {
            return -1;
        }
    }

    private RadioButton getAnswer(int index) {
        switch (index) {
            case 0:
                return answer1;
            case 1:
                return answer2;
            case 2:
                return answer3;
            default:
                return null;
        }
    }

    private void updateQuestion() {
        if (currentQuestionIndex < questions.length) {
            finalAnswer = -1;
            Question question = questions[currentQuestionIndex];

            progressText.setText("Question " + String.valueOf(currentQuestionIndex + 1) + "/5");
            questionTitle.setText(question.getQuestion());


            String[] answers = question.getAnswers();
            answer1.setText(answers[0]);
            answer2.setText(answers[1]);
            answer3.setText(answers[2]);
            answer4.setText(answers[3]);
            answer1.setBackgroundResource(0);
            answer2.setBackgroundResource(0);
            answer3.setBackgroundResource(0);
            answer4.setBackgroundResource(0);
            answer1.setEnabled(true);
            answer2.setEnabled(true);
            answer3.setEnabled(true);
            answer4.setEnabled(true);
            submitAnswer.setText("SUBMIT");
            shouldSubmit = true;
        } else {
            showFinalScore();
        }
    }

    private void checkAnswer() {
        String result = "Incorrect.";
        RadioButton correct = getAnswer(questions[currentQuestionIndex].getCorrectAnswerIndex());
        correct.setBackgroundResource(R.drawable.correct_background);
        if (finalAnswer == questions[currentQuestionIndex].getCorrectAnswerIndex()) {
            score++;
            result = "Correct!";
        } else {
            RadioButton incorrect = getAnswer(finalAnswer);
            incorrect.setBackgroundResource(R.drawable.incorrect_background);
        }
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        updateView();
    }

    private void showFinalScore() {
        Intent intent = new Intent(QuizActivity.this, FinalScore.class);
        intent.putExtra("score", score);
        startActivity(intent);
    }
}