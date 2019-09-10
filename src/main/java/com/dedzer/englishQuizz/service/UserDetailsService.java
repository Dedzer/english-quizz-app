package com.dedzer.englishQuizz.service;

import com.dedzer.englishQuizz.entity.UserDetails;
import com.dedzer.englishQuizz.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Autowired
    private UserService userService;

    public void saveUserDetails(UserDetails userDetails){
        userDetailsRepository.save(userDetails);
    }

    public Optional<UserDetails> findUserDetailsByEmail(String email){
        return userDetailsRepository.findUserDetailsByEmail(email);
    }

    public boolean isUserDetailsEmailExists(String email){
        if(findUserDetailsByEmail(email).isPresent()){
            return true;
        } else {
            return false;
        }
    }
}
