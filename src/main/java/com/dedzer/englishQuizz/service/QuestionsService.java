package com.dedzer.englishQuizz.service;

import com.dedzer.englishQuizz.entity.Questions;
import com.dedzer.englishQuizz.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QuestionsService {

    @Autowired
    private QuestionsRepository questionsRepository;

    public Questions findQuestionById(Long id){
        return questionsRepository.findQuestionById(id);
    }
}
