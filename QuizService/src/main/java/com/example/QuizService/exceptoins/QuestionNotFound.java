package com.example.QuizService.exceptoins;

public class QuestionNotFound extends RuntimeException{
    public QuestionNotFound(String message) {
        super(message);
    }
}
