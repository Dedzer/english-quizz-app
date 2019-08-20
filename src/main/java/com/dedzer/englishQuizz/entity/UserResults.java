package com.dedzer.englishQuizz.entity;

import javax.persistence.*;

@Entity
public class UserResults {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "test_id")
    private Test test;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;
    @JoinColumn(name = "achieved_points")
    private Integer achievedPoints;

    public UserResults() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getAchievedPoints() {
        return achievedPoints;
    }

    public void setAchievedPoints(Integer achievedPoints) {
        this.achievedPoints = achievedPoints;
    }
}
