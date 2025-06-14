package com.example.QuizService.feign;

import com.example.QuizService.dto.QuestionDto;
import com.example.QuizService.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTIONSERVICE")
public interface QuizInterface {

    @GetMapping("question/generate")
    public List<Integer> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam int numQuestions);

    @PostMapping("question/getQuestions")
    public List<QuestionDto> getQuestionsFromId(@RequestBody List<Integer> questionIds);

    @PostMapping("question/getScore")
    public int getScore(@RequestBody List<Response>responses);
}
