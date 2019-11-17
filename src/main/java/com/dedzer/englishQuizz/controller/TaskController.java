package com.dedzer.englishQuizz.controller;

import com.dedzer.englishQuizz.entity.Task;
import com.dedzer.englishQuizz.service.TaskService;
import com.dedzer.englishQuizz.service.TestService;
import com.dedzer.englishQuizz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @Autowired
    private TestService testService;

    @GetMapping("/createtask")
    public ModelAndView createTask(@RequestParam("testId") Long testId){
        ModelAndView modelAndView = new ModelAndView("create-task");
        modelAndView.addObject("getUserRole", userService.getCurrentUser().getRole());
        Task task = new Task();
        task.setTest(testService.getTestById(testId));
        modelAndView.addObject("newTask", task);
        return modelAndView;
    }

    @PostMapping("/createtask")
    public ModelAndView createTask(@ModelAttribute("newTask") Task task, RedirectAttributes attributes){
        ModelAndView modelAndView = new ModelAndView("redirect:admintestpage");
        if(userService.getCurrentUser().getRole().equals("ROLE_ADMIN")){
            taskService.saveTask(task);
            attributes.addFlashAttribute("message", "Task was successfully added!");
        }
        return modelAndView;
    }

    @GetMapping("/updatetask")
    public ModelAndView updateTask(@RequestParam Long id){
        ModelAndView modelAndView = new ModelAndView("update-task");
        modelAndView.addObject("getUserRole", userService.getCurrentUser().getRole());
        modelAndView.addObject("taskToUpdate" ,taskService.getTaskById(id));
        return modelAndView;
    }

    @PostMapping("/updatetask")
    public String updateTask(@ModelAttribute("taskToUpdate") Task task, RedirectAttributes attributes){
        if(userService.getCurrentUser().getRole().equals("ROLE_ADMIN")){
            taskService.updateTask(task);
            attributes.addFlashAttribute("message", "Task was successfully updated!");
        }
        return "redirect:admintestpage";
    }

    @PostMapping("/deletetask")
    public String deleteTask(@RequestParam Long id, RedirectAttributes attributes){
        if(userService.getCurrentUser().getRole().equals("ROLE_ADMIN")){
            taskService.deleteTask(id);
            attributes.addFlashAttribute("message", "Task was successfully deleted!");
        }
        return "redirect:admintestpage";
    }
}
