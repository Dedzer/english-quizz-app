package com.dedzer.englishQuizz.service;

import com.dedzer.englishQuizz.entity.Questions;
import com.dedzer.englishQuizz.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class QuestionsService {

    @Autowired
    private QuestionsRepository questionsRepository;

    public Questions findQuestionById(Long id) {
        return questionsRepository.findOne(id);
    }

    public void saveQuestion(Questions question){
        questionsRepository.save(question);
    }

    public void updateQuestion(Questions question){
        Questions questionFromRepository = questionsRepository.getOne(question.getId());
        if(questionFromRepository != null){
            questionsRepository.save(question);
        }
    }

    public void deleteQuestion(Long id){
        questionsRepository.delete(id);
    }

}
