package com.dedzer.englishQuizz.controller;

import com.dedzer.englishQuizz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }
}
