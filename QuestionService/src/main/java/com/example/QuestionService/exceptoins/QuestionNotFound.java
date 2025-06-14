package com.example.QuestionService.exceptoins;

public class QuestionNotFound extends RuntimeException{
    public QuestionNotFound(String message) {
        super(message);
    }
}
