package com.dedzer.englishQuizz.controller;

import com.dedzer.englishQuizz.entity.Task;
import com.dedzer.englishQuizz.service.TaskService;
import com.dedzer.englishQuizz.service.TestService;
import com.dedzer.englishQuizz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        ModelAndView modelAndView = new ModelAndView("createTask");
        Task task = new Task();
        task.setTest(testService.getTestById(testId));
        modelAndView.addObject("newTask", task);
        return modelAndView;
    }

    @PostMapping("/createtask")
    public ModelAndView createTask(@ModelAttribute("newTask") Task task){
        ModelAndView modelAndView = new ModelAndView("redirect:admintestpage");
        if(userService.getCurrentUser().getRole().equals("ROLE_ADMIN")){
            taskService.saveTask(task);
        }
        return modelAndView;
    }

    @GetMapping("/updatetask")
    public ModelAndView updateTask(@RequestParam Long id){
        ModelAndView modelAndView = new ModelAndView("updateTask");
        modelAndView.addObject("taskToUpdate" ,taskService.getTaskById(id));
        return modelAndView;
    }

    @PostMapping("/updatetask")
    public String updateTask(@ModelAttribute Task task){
        if(userService.getCurrentUser().getRole().equals("ROLE_ADMIN")){
            taskService.updateTask(task);
        }
        return "redirect:admintestpage";
    }

    @PostMapping("/deletetask")
    public String deleteTask(@RequestParam Long id){
        if(userService.getCurrentUser().getRole().equals("ROLE_ADMIN")){
            taskService.deleteTask(id);
        }
        return "redirect:admintestpage";
    }
}
