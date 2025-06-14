package com.example.QuizService.service;


import com.example.QuizService.dto.QuestionDto;
import com.example.QuizService.feign.QuizInterface;
import com.example.QuizService.mapper.Mapper;
import com.example.QuizService.model.Quiz;
import com.example.QuizService.model.Response;
import com.example.QuizService.repository.QuizDao;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public Quiz createQuiz(String category, int numQ, String title){
        List<Integer> questions=quizInterface.getQuestionsForQuiz(category,numQ);
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);
        return quiz;

    }

    public List<QuestionDto> getQuiz(int id) {
        Quiz quiz=quizDao.findById(id).get();
        List<Integer> questionDtos=quiz.getQuestionIds();
       List<QuestionDto> ques= quizInterface.getQuestionsFromId(questionDtos);
        return ques;
    }



    public int calculateresult(int id, List<Response> responses) {
        int right=0;
        right=quizInterface.getScore(responses);
        return right;
    }
    }
