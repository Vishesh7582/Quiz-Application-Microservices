package com.example.QuestionService.mapper;


import com.example.QuestionService.dto.QuestionDto;
import com.example.QuestionService.model.Question;

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
