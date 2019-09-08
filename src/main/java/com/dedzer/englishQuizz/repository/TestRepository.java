package com.dedzer.englishQuizz.repository;

import com.dedzer.englishQuizz.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TestRepository extends JpaRepository<Test, Long> {
    @Query("select t from Test t where t.type=?1")
    List<Test> findAllTestsByType(String type);
}
