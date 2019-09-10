package com.dedzer.englishQuizz.validator;

import com.dedzer.englishQuizz.annotations.UniqueEmail;
import com.dedzer.englishQuizz.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && !userDetailsService.isUserDetailsEmailExists(email);
    }
}
