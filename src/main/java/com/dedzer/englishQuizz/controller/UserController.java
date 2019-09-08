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
    public ModelAndView addNewUser(@ModelAttribute User user) {
        ModelAndView modelAndView;
        if(user.getPassword().equals(user.getConfirmPassword()) && !userService.getUserByLogin(user.getLogin()).isPresent()){
            modelAndView = new ModelAndView("redirect:login");
            userService.addUser(user);
        } else {
            modelAndView = new ModelAndView("redirect:register");
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
    public ModelAndView changePassword(@ModelAttribute User user, @ModelAttribute("oldPassword") String oldPassword){
        ModelAndView modelAndView = new ModelAndView("myprofile");
        userService.changePassword(user, oldPassword);
        return modelAndView;
    }
}
