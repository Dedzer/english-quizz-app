package com.dedzer.englishQuizz.service;

import com.dedzer.englishQuizz.entity.Test;
import com.dedzer.englishQuizz.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;


    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    public Test getTestById(Long id) {
        return testRepository.findOne(id);
    }

    public List<Test> getAllTestsByType(String type){
        return testRepository.findAllTestsByType(type);
    }
}
