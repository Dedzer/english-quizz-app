package com.dedzer.englishQuizz.controller;

import com.dedzer.englishQuizz.service.TestService;
import com.dedzer.englishQuizz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;
    @Autowired
    private TestService testService;

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("getUserRole", userService.getCurrentUser().getRole());
        return modelAndView;
    }
}
