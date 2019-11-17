package com.dedzer.englishQuizz.controller;

import com.dedzer.englishQuizz.entity.User;
import com.dedzer.englishQuizz.entity.UserDetails;
import com.dedzer.englishQuizz.service.TestService;
import com.dedzer.englishQuizz.service.UserResultsService;
import com.dedzer.englishQuizz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public ModelAndView addNewUser(@ModelAttribute @Valid User user, BindingResult result, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView;
        if(!result.hasErrors()){
            modelAndView = new ModelAndView("redirect:login");
            redirectAttributes.addFlashAttribute("message", "You have been registered successfully!");
            userService.addUser(user);
        } else {
            modelAndView = new ModelAndView("register");
        }
        return modelAndView;
    }

    @GetMapping("/myprofile")
    public ModelAndView myProfilePage() {
        ModelAndView modelAndView = new ModelAndView("my-profile");
        modelAndView.addObject("userResultsList", userResultsService.getUserResultsByCurrentUserId());
        modelAndView.addObject("getUserRole", userService.getCurrentUser().getRole());
        modelAndView.addObject("getUser", userService.getUserByLogin(userService.getCurrentUser().getLogin()).get());
        modelAndView.addObject("userToUpdate", new User());
        modelAndView.addObject("detailsToUpdate", new UserDetails());
        return modelAndView;
    }

    @PostMapping("/updatepassword")
    public ModelAndView changePassword(@ModelAttribute User user, @ModelAttribute("oldPassword") String oldPassword){
        ModelAndView modelAndView = new ModelAndView("my-profile");
        userService.changePassword(user, oldPassword);
        return modelAndView;
    }

    @PostMapping("/updatedetails")
    public ModelAndView changeDetails(@ModelAttribute("detailsToUpdate") UserDetails userDetails){
        ModelAndView modelAndView = new ModelAndView("redirect:myprofile");
        userService.changeDetails(userDetails);
        return modelAndView;
    }

    @PostMapping("/updateemail")
    public String changeEmail(@ModelAttribute("detailsToUpdate") @Valid UserDetails userDetails, BindingResult result, RedirectAttributes attributes){
        if (!result.hasFieldErrors("email")){
            userService.changeEmail(userDetails);
            return "redirect:myprofile";
        } else {
            attributes.addFlashAttribute("emailError", result.getFieldError("email").getDefaultMessage());
            return "redirect:myprofile";
        }
    }
}
