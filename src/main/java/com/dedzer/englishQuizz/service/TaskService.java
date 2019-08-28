package com.dedzer.englishQuizz.service;

import com.dedzer.englishQuizz.entity.Task;
import com.dedzer.englishQuizz.entity.Test;
import com.dedzer.englishQuizz.repository.TaskRepository;
import com.dedzer.englishQuizz.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {


    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getTaskByTestId(Long id){
        return taskRepository.findAllTaskByTestId(id);
    }

}
