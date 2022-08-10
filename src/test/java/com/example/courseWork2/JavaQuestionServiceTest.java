package com.example.courseWork2;

import com.example.courseWork2.exception.QuestionAlreadyExistException;
import com.example.courseWork2.exception.QuestionNotFoundException;
import com.example.courseWork2.service.QuestionService;
import com.example.courseWork2.service.impl.JavaQuestionService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class JavaQuestionServiceTest {

    private final QuestionService questionService = new JavaQuestionService();

    @ParameterizedTest
    @MethodSource ("question")
    public void shouldAddQuestion(String question, String answer){
        questionService.add(question, answer);
        assertThatExceptionOfType(QuestionAlreadyExistException.class)
                .isThrownBy(() -> questionService.add(question, answer));
        Assertions.assertThat(questionService.getAll()).containsExactlyInAnyOrder(new Question(question, answer));
    }


    @ParameterizedTest
    @MethodSource ("questions")
    public void addQuestionTest(Question question){
        questionService.add(question);
        Assertions.assertThatExceptionOfType(QuestionAlreadyExistException.class)
                .isThrownBy(()->questionService.add(question));
        Assertions.assertThat(questionService.getAll()).containsExactlyInAnyOrder(question);
    }

    @ParameterizedTest
    @MethodSource ("questions")
    public void removeQuestionTest(Question question){
        questionService.add(question);
        questionService.remove(question);
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(()->questionService.remove(question));

    }
    @ParameterizedTest
    @MethodSource ("question2")
    public void getRandomTest(Set<Question> questions){
        questions.forEach(questionService::add);
        Assertions.assertThat(questionService.getAll()).hasSize(questions.size());
assertThat(questionService.getRandomQuestion()).isIn(questionService.getAll());
    }

public static Stream<Arguments> questions(){
        return Stream.of(
                Arguments.of(new Question("Question", "answer"))
        );
}
public static Stream<Arguments> question(){
        return Stream.of(
                Arguments.of( "Question", "Answer")
        );
    }
    public static Stream<Arguments> question2(){
        return Stream.of(
                Arguments.of(Set.of(new Question("Question", "Answer"), new Question("Question2", "Answer2"),new Question("Question3", "Answer3")))

        );
    }
}