package com.dedzer.englishQuizz.repository;

import com.dedzer.englishQuizz.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionsRepository extends JpaRepository<Questions, Long> {

}
