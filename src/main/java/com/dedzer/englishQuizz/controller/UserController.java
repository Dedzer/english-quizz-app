package com.dedzer.englishQuizz.controller;

import com.dedzer.englishQuizz.entity.User;
import com.dedzer.englishQuizz.service.TestService;
import com.dedzer.englishQuizz.service.UserResultsService;
import com.dedzer.englishQuizz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserService userService;
    private UserResultsService userResultsService;
    private TestService testService;

    @Autowired
    public UserController(UserService userService, UserResultsService userResultsService, TestService testService) {
        this.userService = userService;
        this.userResultsService = userResultsService;
        this.testService = testService;
    }

    @GetMapping("/register")
    public ModelAndView registerPage(Model model) {
        return new ModelAndView("register", "user", new User());
    }

    @PostMapping("/register-process")
    public ModelAndView addNewUser(@ModelAttribute @Valid User user, BindingResult result, Errors errors) {
        ModelAndView modelAndView;
        if (!result.hasErrors()) {
            modelAndView = new ModelAndView("redirect:login");
            userService.addUser(user);
        } else {
            modelAndView = new ModelAndView("register");
        }
        return modelAndView;
    }

    @GetMapping("/myprofile")
    public ModelAndView myProfilePage() {
        ModelAndView modelAndView = new ModelAndView("myprofile");
        modelAndView.addObject("userResultsList", userResultsService.getUserResultsByCurrentUserId());
        modelAndView.addObject("testList", testService.getAllTests());
        modelAndView.addObject("getUserRole", userService.getCurrentUser().getRole());
        modelAndView.addObject("getListening", testService.getAllTestsByType("listening"));
        modelAndView.addObject("getGrammar", testService.getAllTestsByType("grammar"));
        modelAndView.addObject("getReading", testService.getAllTestsByType("reading"));
        return modelAndView;
    }

    @PostMapping("/change-password")
    public ModelAndView changePassword(@ModelAttribute User user, @ModelAttribute("oldPassword") String oldPassword) {
        ModelAndView modelAndView = new ModelAndView("myprofile");
        userService.changePassword(user, oldPassword);
        return modelAndView;
    }
}
