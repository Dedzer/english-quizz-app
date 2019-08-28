package com.dedzer.englishQuizz.repository;

import com.dedzer.englishQuizz.entity.Options;
import com.dedzer.englishQuizz.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionsRepository extends JpaRepository<Questions, Long> {

    Questions findQuestionById(Long id);
}
