package com.dedzer.englishQuizz.annotations;

import com.dedzer.englishQuizz.validator.UniqueLoginValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueLoginValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface UniqueLogin {

    public String message() default "The user with this login has already exist!";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
