package com.dedzer.englishQuizz.service;

import com.dedzer.englishQuizz.entity.User;
import com.dedzer.englishQuizz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public void addUser(User user) {
        user.setRole("ROLE_USER");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    private String getCurrentLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public User getCurrentUser() {
        return userRepository.findUserByLogin(getCurrentLogin()).get();
    }
    public Optional<User> getUserByLogin(String login){
        return userRepository.findUserByLogin(login);
    }

    public void changePassword(User user, String oldPassword){
        if(bCryptPasswordEncoder.matches(oldPassword, getCurrentUser().getPassword())){
            if(user.getPassword().equals(user.getConfirmPassword())){
                userRepository.updatePassword(bCryptPasswordEncoder.encode(user.getPassword()), getCurrentUser().getId());
            }
        }
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addAdmin(User user){
        if(user.getRole().equals("ROLE_USER")){
            userRepository.changeUserRole("ROLE_ADMIN", user.getId());
        }
    }
    public void deleteAdmin(User user){
        if(user.getRole().equals("ROLE_ADMIN")){
            userRepository.changeUserRole("ROLE_USER", user.getId());
        }
    }
}
