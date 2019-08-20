package com.dedzer.englishQuizz.repository;

import com.dedzer.englishQuizz.entity.Options;
import com.dedzer.englishQuizz.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionsRepository extends JpaRepository<Questions, Long> {

    @Query("select q from Questions q where q.task.id=?1")
    List<Questions> findAllQuestionsByTaskId(Long taskId);
}
