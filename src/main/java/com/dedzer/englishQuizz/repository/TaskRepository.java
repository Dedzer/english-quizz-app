package com.dedzer.englishQuizz.repository;

import com.dedzer.englishQuizz.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("select t from Task t where t.test.id=?1")
    List<Task> findAllTaskByTestId(Long testId);
}
