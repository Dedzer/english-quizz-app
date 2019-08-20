package com.dedzer.englishQuizz.controller;

import com.dedzer.englishQuizz.entity.User;
import com.dedzer.englishQuizz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public ModelAndView registerPage(){
        return new ModelAndView("register", "UserToInsert", new User());
    }

    @PostMapping("/register-process")
    public String addNewUser(@ModelAttribute User user){
        userService.addUser(user);
        return "redirect:login";
    }
}
