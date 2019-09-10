package com.dedzer.englishQuizz.controller;

import com.dedzer.englishQuizz.entity.User;
import com.dedzer.englishQuizz.service.TestService;
import com.dedzer.englishQuizz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    TestService testService;

    @RequestMapping("/adminpage")
    public ModelAndView adminPage() {
        ModelAndView modelAndView;
        if (userService.getCurrentUser().getRole().equals("ROLE_ADMIN")) {
            modelAndView = new ModelAndView("adminpage");
            modelAndView.addObject("getUserRole", userService.getCurrentUser().getRole());
            modelAndView.addObject("allUsers", userService.getAllUsers());
            modelAndView.addObject("currentUserId", userService.getCurrentUser().getId());
            modelAndView.addObject("getListening", testService.getAllTestsByType("listening"));
            modelAndView.addObject("getGrammar", testService.getAllTestsByType("grammar"));
            modelAndView.addObject("getReading", testService.getAllTestsByType("reading"));
        } else {
            modelAndView = new ModelAndView("redirect:index");
        }
        return modelAndView;
    }

    @PostMapping("/addadmin")
    public String addAdmin(@ModelAttribute User user) {
        userService.addAdmin(user);
        return "redirect:adminpage";
    }

    @PostMapping("/deleteadmin")
    public String deleteAdmin(@ModelAttribute User user) {
        userService.deleteAdmin(user);
        return "redirect:adminpage";
    }
}
