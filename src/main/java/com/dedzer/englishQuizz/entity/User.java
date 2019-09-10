package com.dedzer.englishQuizz.entity;

import com.dedzer.englishQuizz.annotations.PasswordMatchers;
import com.dedzer.englishQuizz.annotations.UniqueLogin;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@PasswordMatchers
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @UniqueLogin
    private String login;
    private String password;
    @Transient
    private String confirmPassword;
    private String role;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_details_id")
    private UserDetails userDetails;
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<UserResults> userResults;

    public User() {
    }

    public List<UserResults> getUserResults() {
        return userResults;
    }

    public void setUserResultsList(List<UserResults> userResults) {
        this.userResults = userResults;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setUserResults(List<UserResults> userResults) {
        this.userResults = userResults;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
