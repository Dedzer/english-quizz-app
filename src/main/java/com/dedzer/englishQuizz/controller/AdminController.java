package com.dedzer.englishQuizz.controller;

import com.dedzer.englishQuizz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @RequestMapping("/adminpage")
    public ModelAndView adminPage(){
        ModelAndView modelAndView;
        if(userService.getCurrentUser().getRole().equals("ROLE_ADMIN")){
            modelAndView = new ModelAndView("adminpage");
            modelAndView.addObject("getUserRole", userService.getCurrentUser().getRole());
        } else {
            modelAndView = new ModelAndView("redirect:index");
        }
        return modelAndView;
    }
}
