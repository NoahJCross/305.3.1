package com.example.a31;

// Question class representing a single quiz question
public class Question {

    // Member variables
    private String title;
    private String[] answers;
    private int correctAnswerIndex;

    // Constructor to initialize question properties
    public Question(String title, String[] answers, int correctAnswerIndex) {
        this.title = title;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    // Method to get the question title
    public String getQuestion() {
        return title;
    }

    // Method to get the array of possible answers
    public String[] getAnswers() {
        return answers;
    }

    // Method to get the index of the correct answer
    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}
