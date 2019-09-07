package com.dedzer.englishQuizz.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_results")
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
    @Column(name = "achieved_points")
    private Integer achievedPoints;
    @Column(name = "accomplished_date")
    private Date accomplishedDate;

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

    public Date getAccomplishedDate() {
        return accomplishedDate;
    }

    public void setAccomplishedDate(Date accomplishedDate) {
        this.accomplishedDate = accomplishedDate;
    }
}
