package com.example.a31;

public class Question {
    private String title;
    private String[] answers;
    private int correctAnswerIndex;

    public Question(String title, String[] answers, int correctAnswerIndex) {
        this.title = title;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return title;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}
