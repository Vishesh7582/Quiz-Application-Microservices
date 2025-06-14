package com.example.QuizService.controller;


import com.example.QuizService.dto.QuestionDto;
import com.example.QuizService.dto.QuizDto;
import com.example.QuizService.model.Quiz;
import com.example.QuizService.model.Response;
import com.example.QuizService.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/quiz")
@RestController
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDto quizDto){
           return new ResponseEntity<>(quizService.createQuiz(quizDto.getCategory(), quizDto.getNumQ(), quizDto.getTitle()), HttpStatus.OK);
    }


    @GetMapping("get/{id}")
    public List<QuestionDto> getQuizQuestions(@PathVariable int id){
       return quizService.getQuiz(id);
    }
    @PostMapping("submit/{id}")
    public int submitQuiz(@PathVariable int id, @RequestBody List<Response>responses){
        return quizService.calculateresult(id,responses);
    }
}
