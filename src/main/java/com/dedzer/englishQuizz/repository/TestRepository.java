package com.dedzer.englishQuizz.repository;

import com.dedzer.englishQuizz.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TestRepository extends JpaRepository<Test, Long> {

}
