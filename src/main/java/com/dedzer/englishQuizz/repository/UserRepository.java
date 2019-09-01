package com.dedzer.englishQuizz.repository;

import com.dedzer.englishQuizz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByLogin(String login);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update user u set u.password =:password where u.id =:id", nativeQuery = true)
    void updatePassword(@Param("password") String password, @Param("id")Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update user u set u.role =:role where u.id =:id", nativeQuery = true)
    void changeUserRole(@Param("role") String role, @Param("id")Long id);

}
