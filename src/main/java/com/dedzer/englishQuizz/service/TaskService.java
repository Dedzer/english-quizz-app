package com.dedzer.englishQuizz.service;

import com.dedzer.englishQuizz.entity.Task;
import com.dedzer.englishQuizz.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {


    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getTaskByTestId(Long id) {
        return taskRepository.findAllTaskByTestId(id);
    }

    public void saveTask(Task task){
        taskRepository.save(task);
    }

    public void updateTask(Task task){
        Task taskFromRepository = taskRepository.findOne(task.getId());
        if(taskFromRepository != null){
            taskRepository.save(task);
        }
    }

    public Task getTaskById(Long id){
       return taskRepository.findOne(id);
    }

    public void deleteTask(Long id){
        taskRepository.delete(id);
    }
}
