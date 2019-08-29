package com.dedzer.englishQuizz.controller;

import com.dedzer.englishQuizz.entity.User;
import com.dedzer.englishQuizz.service.TestService;
import com.dedzer.englishQuizz.service.UserResultsService;
import com.dedzer.englishQuizz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView registerPage() {
        return new ModelAndView("register", "UserToInsert", new User());
    }

    @PostMapping("/register-process")
    public String addNewUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:login";
    }

    @GetMapping("/myprofile")
    public ModelAndView myProfilePage() {
        ModelAndView modelAndView = new ModelAndView("myprofile");
        modelAndView.addObject("userResultsList", userResultsService.getUserResultsByCurrentUserId());
        modelAndView.addObject("testList", testService.getAllTests());
        return modelAndView;
    }
}
