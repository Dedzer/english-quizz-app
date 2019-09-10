package com.dedzer.englishQuizz.validator;

import com.dedzer.englishQuizz.annotations.PasswordMatchers;
import com.dedzer.englishQuizz.entity.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchersValidator implements ConstraintValidator<PasswordMatchers, Object> {

    @Override
    public void initialize(PasswordMatchers constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        User user = (User) obj;
        return user.getPassword().equals(user.getConfirmPassword());
    }
}
