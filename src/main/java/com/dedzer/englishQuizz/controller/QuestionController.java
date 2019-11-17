package com.dedzer.englishQuizz.controller;

import com.dedzer.englishQuizz.entity.Questions;
import com.dedzer.englishQuizz.service.QuestionsService;
import com.dedzer.englishQuizz.service.TaskService;
import com.dedzer.englishQuizz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class QuestionController {

    @Autowired
    private QuestionsService questionsService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @GetMapping("/createquestion")
    public ModelAndView createQuestion(@RequestParam("taskId") Long taskId){
        ModelAndView modelAndView = new ModelAndView("create-question");
        modelAndView.addObject("getUserRole", userService.getCurrentUser().getRole());
        Questions question = new Questions();
        question.setTask(taskService.getTaskById(taskId));
        modelAndView.addObject("newQuestion", question);
        return modelAndView;
    }

    @PostMapping("/createquestion")
    public ModelAndView createQuestion(@ModelAttribute Questions questions, RedirectAttributes attributes){
        ModelAndView modelAndView = new ModelAndView("redirect:admintestpage");
        if(userService.getCurrentUser().getRole().equals("ROLE_ADMIN")){
            questionsService.saveQuestion(questions);
            attributes.addFlashAttribute("message", "Question was successfully added!");
        }
        return modelAndView;
    }

    @GetMapping("/updatequestion")
    public ModelAndView updateQuestion(@RequestParam Long id){
        ModelAndView modelAndView = new ModelAndView("update-question");
        modelAndView.addObject("getUserRole", userService.getCurrentUser().getRole());
        modelAndView.addObject("questionToUpdate", questionsService.findQuestionById(id));
        return modelAndView;
    }

    @PostMapping("/updatequestion")
    public String updateQuestion(@ModelAttribute("questionToUpdate") Questions question, RedirectAttributes attributes){
        if(userService.getCurrentUser().getRole().equals("ROLE_ADMIN")){
            questionsService.updateQuestion(question);
            attributes.addFlashAttribute("message", "Question was successfully updated!");
        }
        return "redirect:admintestpage";
    }

    @PostMapping("/deletequestion")
    public String deleteQuestion(@RequestParam Long id, RedirectAttributes attributes){
        questionsService.deleteQuestion(id);
        attributes.addFlashAttribute("message", "Question was successfully deleted!");
        return "redirect:admintestpage";
    }
}
