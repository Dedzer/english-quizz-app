package com.dedzer.englishQuizz.repository;

import com.dedzer.englishQuizz.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
}
