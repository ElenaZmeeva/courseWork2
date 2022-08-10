package com.example.courseWork2.service.impl;

import com.example.courseWork2.Question;
import com.example.courseWork2.exception.QuestionAlreadyExistException;
import com.example.courseWork2.exception.QuestionNotFoundException;
import com.example.courseWork2.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

private final Random RANDOM;

private final Set<Question> questions;

    public JavaQuestionService() {
        this.questions = new HashSet<>();
        this.RANDOM=new Random();
    }


    @Override
    public Question add(String question, String answer) {
        return add(new Question(question,answer));

    }

    @Override
    public Question add(Question question) {
        if(questions.contains(question)) {
            throw new QuestionAlreadyExistException();
        }
      questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if(!questions.contains(question)){
            throw new QuestionNotFoundException();
        }
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> questionList=new ArrayList<>(questions);
        return questionList.get(RANDOM.nextInt (questionList.size()));
    }
}
