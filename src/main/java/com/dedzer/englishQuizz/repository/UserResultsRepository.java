package com.dedzer.englishQuizz.repository;
;
import com.dedzer.englishQuizz.entity.UserResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserResultsRepository extends JpaRepository<UserResults, Long> {

    @Query("select u from UserResults u where u.user.id=?1")
    List<UserResults> findAllUserResultsByUserId(Long userId);
}
