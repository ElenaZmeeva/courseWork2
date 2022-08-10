package com.example.courseWork2;


import com.example.courseWork2.exception.NotEnoughQuestionException;
import com.example.courseWork2.service.impl.ExaminerServiceImpl;
import com.example.courseWork2.service.impl.JavaQuestionService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    @Mock
    private JavaQuestionService javaQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    public void getQuestionsNegativeTest(){
        Assertions.assertThatExceptionOfType(NotEnoughQuestionException.class)
                .isThrownBy(()->examinerService.getQuestions(-1));

        Assertions.assertThatExceptionOfType(NotEnoughQuestionException.class)
                .isThrownBy(()->examinerService.getQuestions(1));
    }

    @Test
    public void getQuestionsPositiveTest(){
        List<Question> questions= new ArrayList<>();
        questions.add(new Question("Question1", "Answer1"));
        questions.add(new Question("Question2", "Answer2"));
        questions.add(new Question("Question3", "Answer3"));
        questions.add(new Question("Question4", "Answer4"));
        questions.add(new Question("Question5", "Answer5"));

        when(javaQuestionService.getAll()).thenReturn(questions);

        when(javaQuestionService.getRandomQuestion()).thenReturn(questions.get(0),questions.get(2),questions.get(1),questions.get(0),questions.get(3));
        Assertions.assertThat(examinerService.getQuestions(3)).containsExactlyInAnyOrder(questions.get(0),questions.get(2),questions.get(1));

        when(javaQuestionService.getRandomQuestion()).thenReturn(questions.get(0),questions.get(2),questions.get(1),questions.get(0),questions.get(3),questions.get(4));
        Assertions.assertThat(examinerService.getQuestions(4)).containsExactlyInAnyOrder(questions.get(0),questions.get(2),questions.get(1),questions.get(3));
    }
}
