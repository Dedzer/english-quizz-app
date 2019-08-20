package com.dedzer.englishQuizz.repository;

import com.dedzer.englishQuizz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
