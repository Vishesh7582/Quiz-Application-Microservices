package com.example.QuizService.mapper;


import com.example.QuizService.dto.QuestionDto;
import com.example.QuizService.model.Question;

public class Mapper {
    public static QuestionDto entityToDto(Question question){
        QuestionDto questionDto=new QuestionDto();
        questionDto.setId(question.getId());
        questionDto.setQuestionTitle(question.getQuestionTitle());
        questionDto.setOption1(question.getOption1());
        questionDto.setOption2(question.getOption2());
        questionDto.setOption3(question.getOption3());
        questionDto.setOption4(question.getOption4());
        return questionDto;

    }
}
