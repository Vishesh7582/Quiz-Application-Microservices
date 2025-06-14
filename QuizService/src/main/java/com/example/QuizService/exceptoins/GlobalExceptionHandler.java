package com.example.QuizService.exceptoins;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(QuestionNotFound.class)
    ResponseEntity<String> questionExceptionHandler(QuestionNotFound questionNotFound){
        return new ResponseEntity<>(questionNotFound.getMessage(), HttpStatus.NOT_FOUND);
    }
}
