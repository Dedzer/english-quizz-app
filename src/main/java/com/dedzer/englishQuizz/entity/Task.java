package com.dedzer.englishQuizz.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "test_id")
    private Test test;
    private String task;
    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE)
    private Set<Questions> questionsSet;

    public Task() {
    }

    public Set<Questions> getQuestionsSet() {
        return questionsSet;
    }

    public void setQuestionsSet(Set<Questions> questionsSet) {
        this.questionsSet = questionsSet;
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

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
