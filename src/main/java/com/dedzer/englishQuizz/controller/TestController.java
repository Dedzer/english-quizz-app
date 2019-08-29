package com.dedzer.englishQuizz.controller;


import com.dedzer.englishQuizz.model.UserAnswers;
import com.dedzer.englishQuizz.service.QuestionsService;
import com.dedzer.englishQuizz.service.TaskService;
import com.dedzer.englishQuizz.service.TestService;
import com.dedzer.englishQuizz.service.UserResultsService;
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

    @Autowired
    public TestController(TestService testService, TaskService taskService, QuestionsService questionsService, UserResultsService userResultsService) {
        this.testService = testService;
        this.taskService = taskService;
        this.questionsService = questionsService;
        this.userResultsService = userResultsService;
    }

    @GetMapping("/test")
    public ModelAndView getTest(@RequestParam Long id) {
        ModelAndView modelAndView = new ModelAndView("test");
        modelAndView.addObject("testList", testService.getAllTests());
        modelAndView.addObject("getTest", testService.getTestById(id));
        modelAndView.addObject("taskList", taskService.getTaskByTestId(id));
        modelAndView.addObject("userAnswers", new UserAnswers());
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
        return modelAndView;
    }
}
