package com.dedzer.englishQuizz.model;


import java.util.List;


public class UserAnswers {
    private List<Long> questionId;
    private List<String> answer;
    private Long testId;

    public UserAnswers() {
    }

    public List<Long> getQuestionId() {
        return questionId;
    }

    public void setQuestionId(List<Long> questionId) {
        this.questionId = questionId;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }
}
