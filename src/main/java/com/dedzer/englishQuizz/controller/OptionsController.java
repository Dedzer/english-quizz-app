package com.dedzer.englishQuizz.controller;

import com.dedzer.englishQuizz.entity.Options;
import com.dedzer.englishQuizz.service.OptionsService;
import com.dedzer.englishQuizz.service.QuestionsService;
import com.dedzer.englishQuizz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OptionsController {

    @Autowired
    private OptionsService optionsService;
    @Autowired
    private QuestionsService questionsService;
    @Autowired
    private UserService userService;

    @GetMapping("/createoption")
    public ModelAndView createOption(@RequestParam("questionId") Long questionId){
        ModelAndView modelAndView = new ModelAndView("create-option");
        modelAndView.addObject("getUserRole", userService.getCurrentUser().getRole());
        Options option = new Options();
        option.setQuestions(questionsService.findQuestionById(questionId));
        modelAndView.addObject("newOption", option);
        return modelAndView;
    }

    @PostMapping("/createoption")
    public ModelAndView createOption(@ModelAttribute("newOption") Options option, RedirectAttributes attributes){
        ModelAndView modelAndView = new ModelAndView("redirect:admintestpage");
        if(userService.getCurrentUser().getRole().equals("ROLE_ADMIN")){
            optionsService.saveOption(option);
            attributes.addFlashAttribute("message", "Option was successfully added!");
        }
        return modelAndView;
    }

    @GetMapping("/updateoption")
    public ModelAndView updateOption(@RequestParam Long id){
        ModelAndView modelAndView = new ModelAndView("update-option");
        modelAndView.addObject("getUserRole", userService.getCurrentUser().getRole());
        modelAndView.addObject("optionToUpdate", optionsService.getOptionById(id));
        return modelAndView;
    }

    @PostMapping("/updateoption")
    public String updateOption(@ModelAttribute("optionToUpdate") Options option, RedirectAttributes attributes){
        if(userService.getCurrentUser().getRole().equals("ROLE_ADMIN")){
            optionsService.updateOption(option);
            attributes.addFlashAttribute("message", "Option was successfully updated!");
        }
        return "redirect:admintestpage";
    }

    @PostMapping("/deleteoption")
    public String deleteOption(@RequestParam Long id, RedirectAttributes attributes){
        if(userService.getCurrentUser().getRole().equals("ROLE_ADMIN")){
            optionsService.deleteOption(id);
            attributes.addFlashAttribute("message", "Option was successfully deleted!");
        }
        return "redirect:admintestpage";
    }
}
