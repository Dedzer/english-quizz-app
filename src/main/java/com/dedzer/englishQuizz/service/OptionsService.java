package com.dedzer.englishQuizz.service;

import com.dedzer.englishQuizz.entity.Options;
import com.dedzer.englishQuizz.repository.OptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class OptionsService {

    @Autowired
    private OptionsRepository optionsRepository;

    public void saveOption(Options option){
        optionsRepository.save(option);
    }

    public void updateOption(Options option){
        Options optionFromRepository = optionsRepository.getOne(option.getId());
        if (optionFromRepository != null){
            optionsRepository.save(option);
        }
    }

    public void deleteOption(Long id){
        optionsRepository.delete(id);
    }

    public Options getOptionById(Long id){
        return optionsRepository.getOne(id);
    }
}
