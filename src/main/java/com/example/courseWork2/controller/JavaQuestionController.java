package com.example.courseWork2.controller;

import com.example.courseWork2.Question;
import com.example.courseWork2.service.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {

    private final QuestionService questionService;

    public JavaQuestionController(@Qualifier("javaQuestionService") QuestionService questionService) {
        this.questionService = questionService;
    }

   @GetMapping("/add")
    public Question add (@RequestParam String question,
                         @RequestParam  String answer){
        return questionService.add(question, answer);
   }


    @GetMapping("/remove")
    public Question reemove (@RequestParam String question,
                             @RequestParam  String answer){
        return questionService.remove(new Question(question, answer));
    }

    @GetMapping()
    public Collection<Question> getAll (){
        return questionService.getAll();
    }
}
