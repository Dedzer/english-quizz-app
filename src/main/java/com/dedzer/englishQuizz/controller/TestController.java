package com.dedzer.englishQuizz.controller;


import com.dedzer.englishQuizz.entity.Test;
import com.dedzer.englishQuizz.model.UserAnswers;
import com.dedzer.englishQuizz.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        modelAndView.addObject("getTest", testService.getTestById(id));
        modelAndView.addObject("taskList", taskService.getTaskByTestId(id));
        modelAndView.addObject("userAnswers", new UserAnswers());
        modelAndView.addObject("getUserRole", userService.getCurrentUser().getRole());
        return modelAndView;
    }

    @PostMapping("/result-process")
    public ModelAndView userResult(@ModelAttribute("userAnswers") UserAnswers userAnswers) {
        ModelAndView modelAndView = new ModelAndView("result");
        Map<Long, Boolean> resultMap = userResultsService.getResult(userAnswers.getQuestionId(), userAnswers.getAnswer());
        modelAndView.addObject("getQuestion", questionsService);
        modelAndView.addObject("resultMap", resultMap);
        modelAndView.addObject("achievedPoints", userResultsService.achievedPoints(userAnswers.getTestId(), resultMap));
        modelAndView.addObject("getUserRole", userService.getCurrentUser().getRole());
        modelAndView.addObject("getTestId", userAnswers.getTestId());
        modelAndView.addObject("userAnswer", userAnswers.getAnswer());
        return modelAndView;
    }

    @GetMapping("/tests")
    public ModelAndView allTestsPage(){
        ModelAndView modelAndView = new ModelAndView("tests");
        modelAndView.addObject("getUserRole", userService.getCurrentUser().getRole());
        modelAndView.addObject("getListening", testService.getAllTestsByType("listening"));
        modelAndView.addObject("getGrammar", testService.getAllTestsByType("grammar"));
        modelAndView.addObject("getReading", testService.getAllTestsByType("reading"));
        return modelAndView;
    }

    @GetMapping("/createtest")
    public ModelAndView createTestPage(){
        ModelAndView modelAndView = new ModelAndView("create-test");
        modelAndView.addObject("getUserRole", userService.getCurrentUser().getRole());
        modelAndView.addObject("test", new Test());
        return modelAndView;
    }
    @PostMapping("/createtest")
    public ModelAndView createTest(@ModelAttribute Test test, RedirectAttributes attributes){
        ModelAndView modelAndView = new ModelAndView("redirect:admintestpage");
        if (userService.getCurrentUser().getRole().equals("ROLE_ADMIN")){
            testService.saveTest(test);
            attributes.addFlashAttribute("message", "Test was successfully added!");
        }
        return modelAndView;
    }

    @GetMapping("/updatetest")
    public ModelAndView updateTest(@RequestParam Long id) {
        ModelAndView modelAndView = new ModelAndView("update-test");
        modelAndView.addObject("getUserRole", userService.getCurrentUser().getRole());
        modelAndView.addObject("test", testService.getTestById(id));
        return modelAndView;
    }
    @PostMapping("/updatetest")
    public ModelAndView updateTest(@ModelAttribute Test test, RedirectAttributes attributes){
        ModelAndView modelAndView = new ModelAndView("redirect:admintestpage");
        if (userService.getCurrentUser().getRole().equals("ROLE_ADMIN")){
            testService.updateTest(test);
            attributes.addFlashAttribute("message", "Test was successfully updated!");
        }
        return modelAndView;
    }
    @PostMapping("/deletetest")
    public String deleteTest(@RequestParam Long id, RedirectAttributes attributes){
        if (userService.getCurrentUser().getRole().equals("ROLE_ADMIN")){
            testService.deleteTest(id);
            attributes.addFlashAttribute("message", "Test was successfully deleted!");
        }
        return "redirect:admintestpage";
    }
}
