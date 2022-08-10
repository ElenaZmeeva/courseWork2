package com.example.courseWork2.controller;

import com.example.courseWork2.service.ExaminerService;
import com.example.courseWork2.Question;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam")
public class ExamController {
   private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

@GetMapping("/get/{amount}")
public Collection<Question> getQuestions  (@RequestParam ("amount") int amount){
    return examinerService.getQuestions(amount);
}
}
