package com.dedzer.englishQuizz.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String login;
    private String password;
    private String role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
