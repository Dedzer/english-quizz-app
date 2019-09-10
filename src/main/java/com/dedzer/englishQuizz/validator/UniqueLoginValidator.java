package com.dedzer.englishQuizz.validator;

import com.dedzer.englishQuizz.annotations.UniqueLogin;
import com.dedzer.englishQuizz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(UniqueLogin constraintAnnotation){

    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext context){
        return login != null && !userService.isUserLoginExists(login);
    }
}
