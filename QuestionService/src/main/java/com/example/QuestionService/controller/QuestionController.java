package com.example.QuestionService.controller;



import com.example.QuestionService.dto.QuestionDto;
import com.example.QuestionService.model.Question;
import com.example.QuestionService.model.Response;
import com.example.QuestionService.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        // Pass the question object to the service for saving
        return questionService.addQuestion(question);
    }
    @GetMapping("allQuestions")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }
    @GetMapping("id/{id}")
    public Question getQuestionById(@PathVariable int id){
        return questionService.getById(id);
    }
    @GetMapping("category/{category}")
    public List<Question> getByCategory(@PathVariable String category){
        return questionService.getCategory(category);
    }

    @DeleteMapping("id/{id}")
    public void deleteQuestion(@PathVariable int id){
        questionService.deleteQuestion(id);
    }

    @PutMapping("updateQuestion")
    public Question updateQuestion(@RequestBody Question question){
         return questionService.updateQuestion(question);
    }

    //generate
    //getQuestions
    //getScore

    @GetMapping("generate")
    public List<Integer> getQuestionsForQuiz(@RequestParam String categoryName,@RequestParam int numQuestions){
       return questionService.getQuestionsForQuiz(categoryName,numQuestions);
    }

    @PostMapping("getQuestions")
    public List<QuestionDto> getQuestionsFromId(@RequestBody List<Integer> questionIds){
        return questionService.getQuestionsFromId(questionIds);
    }

    @PostMapping("getScore")
    public int getScore(@RequestBody List<Response>responses){
        return questionService.getScore(responses);
    }

}
