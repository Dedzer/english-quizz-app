package com.dedzer.englishQuizz.service;


import com.dedzer.englishQuizz.entity.User;
import com.dedzer.englishQuizz.entity.UserResults;
import com.dedzer.englishQuizz.repository.UserResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    public Double achievedPoints(Long testId, Map<Long, Boolean> userResult) {
        int points = 0;
        for (Map.Entry<Long, Boolean> entry : userResult.entrySet()) {
            if (entry.getValue().equals(true)) {
                points++;
            }
        }
        double result = ((double)points * 100 / (double)userResult.size());
        double roundedResult = new BigDecimal(result).setScale(2, RoundingMode.UP).doubleValue();
        saveResults(testId, roundedResult);
        return roundedResult;
    }

    public void saveResults(long testId, double result) {
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(stamp.getTime());
        UserResults userResults = new UserResults();
        userResults.setAccomplishedDate(date);
        userResults.setAchievedPoints(result);
        userResults.setUser(userService.getCurrentUser());
        userResults.setTest(testService.getTestById(testId));
        userResultsRepository.save(userResults);
    }

    public Double averageOfUsersResults(){
        List<UserResults> userResults = userResultsRepository.findAll();
        double sum = 0;
        if(!userResults.isEmpty()){
            for (UserResults result : userResults){
                sum += result.getAchievedPoints();
            }
            return new BigDecimal(sum / userResults.size()).setScale(2, RoundingMode.UP).doubleValue();
        }
        return sum;
    }

    public List<UserResults> getUserResultsByCurrentUserId() {
        return userResultsRepository.findAllUserResultsByUserId(userService.getCurrentUser().getId());
    }
}
