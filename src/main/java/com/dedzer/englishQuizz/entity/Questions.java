package com.dedzer.englishQuizz.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "task_id")
    private Task task;
    private String question;
    private String answer;
    @OneToMany(mappedBy = "questions", cascade = CascadeType.PERSIST)
    private Set<Options> optionsSet;

    public Questions() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Set<Options> getOptionsSet() {
        return optionsSet;
    }

    public void setOptionsSet(Set<Options> optionsSet) {
        this.optionsSet = optionsSet;
    }
}
