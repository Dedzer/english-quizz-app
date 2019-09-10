package com.dedzer.englishQuizz.repository;

import com.dedzer.englishQuizz.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    Optional<UserDetails> findUserDetailsByEmail(String email);
}
