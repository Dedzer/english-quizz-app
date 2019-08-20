package com.dedzer.englishQuizz.repository;

import com.dedzer.englishQuizz.entity.Options;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OptionsRepository extends JpaRepository<Options, Long> {

    @Query("select o from Options o where o.question.id=?1")
    List<Options> findAllOptionsByQuestionsId(Long questionId);
}
