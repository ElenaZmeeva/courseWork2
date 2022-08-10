package com.example.courseWork2.service.impl;

import com.example.courseWork2.Question;
import com.example.courseWork2.exception.NotEnoughQuestionException;
import com.example.courseWork2.service.ExaminerService;
import com.example.courseWork2.service.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class ExaminerServiceImpl implements ExaminerService {


    private final QuestionService questionService;

    public ExaminerServiceImpl(@Qualifier("javaQuestionService") QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Collection<Question> questions= questionService.getAll();
        if(amount<=0 || amount >questions.size()){
            throw new NotEnoughQuestionException();
        }
        Set<Question> questionSet=new HashSet<>(amount);
        while (questionSet.size()<amount){
            questionSet.add(questionService.getRandomQuestion());
        }
        return questionSet;
    }
}
