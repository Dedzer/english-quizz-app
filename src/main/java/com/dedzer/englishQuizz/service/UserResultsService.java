package com.dedzer.englishQuizz.service;


import com.dedzer.englishQuizz.entity.User;
import com.dedzer.englishQuizz.entity.UserResults;
import com.dedzer.englishQuizz.repository.UserResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class UserResultsService {

    private UserService userService;
    private QuestionsService questionsService;
    private UserResultsRepository userResultsRepository;
    private TestService testService;

    @Autowired
    public UserResultsService(UserService userService, QuestionsService questionsService, UserResultsRepository userResultsRepository, TestService testService) {
        this.userService = userService;
        this.questionsService = questionsService;
        this.userResultsRepository = userResultsRepository;
        this.testService = testService;
    }

    public Map<Long, Boolean> getResult(List<Long> questionId, List<String> userAnswers) {
        Map<Long, Boolean> userResult = new LinkedHashMap<>();
        for (int i = 0; i < questionId.size(); i++) {
            String[] answer = questionsService.findQuestionById(questionId.get(i)).getAnswer().toLowerCase().split("[^a-zA-Z']+");
            String[] userAnswer = userAnswers.get(i).toLowerCase().split("[^a-zA-Z']+");
            if (Arrays.equals(answer, userAnswer)) {
                userResult.put(questionId.get(i), true);
            } else {
                userResult.put(questionId.get(i), false);
            }
        }
        return userResult;
    }

    public Integer achievedPoints(Long testId, Map<Long, Boolean> userResult) {
        int points = 0;
        for (Map.Entry<Long, Boolean> entry : userResult.entrySet()) {
            if (entry.getValue().equals(true)) {
                points++;
            }
        }
        saveResults(testId, points);
        return points;
    }

    public void saveResults(long testId, int points) {
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(stamp.getTime());
        UserResults userResults = new UserResults();
        userResults.setAccomplishedDate(date);
        userResults.setAchievedPoints(points);
        userResults.setUser(userService.getCurrentUser());
        userResults.setTest(testService.getTestById(testId));
        userResultsRepository.save(userResults);
    }

    public List<UserResults> getUserResultsByCurrentUserId() {
        return userResultsRepository.findAllUserResultsByUserId(userService.getCurrentUser().getId());
    }
}
