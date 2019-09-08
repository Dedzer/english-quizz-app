package com.dedzer.englishQuizz.controller;


import com.dedzer.englishQuizz.model.UserAnswers;
import com.dedzer.englishQuizz.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class TestController {

    private TestService testService;
    private TaskService taskService;
    private QuestionsService questionsService;
    private UserResultsService userResultsService;
    private UserService userService;

    @Autowired
    public TestController(TestService testService, TaskService taskService, QuestionsService questionsService, UserResultsService userResultsService, UserService userService) {
        this.testService = testService;
        this.taskService = taskService;
        this.questionsService = questionsService;
        this.userResultsService = userResultsService;
        this.userService = userService;
    }

    @GetMapping("/test")
    public ModelAndView getTest(@RequestParam Long id) {
        ModelAndView modelAndView = new ModelAndView("test");
        modelAndView.addObject("testList", testService.getAllTests());
        modelAndView.addObject("getTest", testService.getTestById(id));
        modelAndView.addObject("taskList", taskService.getTaskByTestId(id));
        modelAndView.addObject("userAnswers", new UserAnswers());
        modelAndView.addObject("getUserRole", userService.getCurrentUser().getRole());
        modelAndView.addObject("getListening", testService.getAllTestsByType("listening"));
        modelAndView.addObject("getGrammar", testService.getAllTestsByType("grammar"));
        modelAndView.addObject("getReading", testService.getAllTestsByType("reading"));
        return modelAndView;
    }

    @PostMapping("/result-process")
    public ModelAndView userResult(@ModelAttribute("userAnswers") UserAnswers userAnswers) {
        ModelAndView modelAndView = new ModelAndView("result");
        Map<Long, Boolean> resultMap = userResultsService.getResult(userAnswers.getQuestionId(), userAnswers.getAnswer());
        modelAndView.addObject("testList", testService.getAllTests());
        modelAndView.addObject("getQuestion", questionsService);
        modelAndView.addObject("resultMap", resultMap);
        modelAndView.addObject("achievedPoints", userResultsService.achievedPoints(userAnswers.getTestId(), resultMap));
        modelAndView.addObject("getUserRole", userService.getCurrentUser().getRole());
        modelAndView.addObject("getListening", testService.getAllTestsByType("listening"));
        modelAndView.addObject("getGrammar", testService.getAllTestsByType("grammar"));
        modelAndView.addObject("getReading", testService.getAllTestsByType("reading"));
        modelAndView.addObject("getTestId", userAnswers.getTestId());
        return modelAndView;
    }
}
