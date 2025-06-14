package com.example.QuestionService.service;

import com.example.QuestionService.dto.QuestionDto;
import com.example.QuestionService.exceptoins.QuestionNotFound;
import com.example.QuestionService.mapper.Mapper;
import com.example.QuestionService.model.Question;
import com.example.QuestionService.model.Response;
import com.example.QuestionService.repository.QuestionDao;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> addQuestion(Question question) {
        // Check if the question object is null
        if (question == null) {
            return new ResponseEntity<>("Question is null", HttpStatus.BAD_REQUEST);
        }

        try {

            // Save the question in the database
            questionDao.save(question);

            // Return success response if the question is saved
            return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception and return an error response
            e.printStackTrace(); // For debugging purposes
            return new ResponseEntity<>("Failed to add question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public List<Question> getAllQuestions(){
        return questionDao.findAll();
    }
    public Question getById(int id){
        return questionDao.findById(id).orElseThrow(()->new QuestionNotFound("Question with id:"+id+"is not found"));
    }
    public List<Question> getCategory(String category){
        return questionDao.findByCategory(category);
    }

    public void deleteQuestion(int id) {
        questionDao.deleteById(id);
    }

    public Question updateQuestion(Question updatedQuestion) {
        Question que= questionDao.findById(updatedQuestion.getId()).orElseThrow(()->new QuestionNotFound("Question is not found"));
        if(que!=null) {
            que.setQuestionTitle(updatedQuestion.getQuestionTitle());
            que.setOption1(updatedQuestion.getOption1());
            que.setOption2(updatedQuestion.getOption2());
            que.setOption3(updatedQuestion.getOption3());
            que.setOption4(updatedQuestion.getOption4());
            que.setRightAnswer(updatedQuestion.getRightAnswer());
            que.setDifficultyLevel(updatedQuestion.getDifficultyLevel());
            que.setCategory(updatedQuestion.getCategory());
            questionDao.save(que);
        }
        return que;

    }


    public List<Integer> getQuestionsForQuiz(String categoryName, int numQuestions) {
        return questionDao.findRandomQuestionsByCategory(categoryName,numQuestions);
    }

    public List<QuestionDto> getQuestionsFromId(List<Integer> questionIds) {
        List<Question> questions=new ArrayList<>();
        for(Integer id:questionIds){
           questions.add(questionDao.findById(id).get());
        }
        List<QuestionDto> questionDtos=new ArrayList<>();
        for(Question q:questions){
            questionDtos.add(Mapper.entityToDto(q));
        }
        return questionDtos;
    }

    public int getScore(List<Response> responses) {
        int right=0;

        for(Response r: responses){
            Question q=questionDao.findById(r.getId()).get();
            if(r.getResponse().equalsIgnoreCase(q.getRightAnswer())){
                right++;
            }
        }
        return right;
    }
}
