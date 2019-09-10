package com.dedzer.englishQuizz.annotations;

import com.dedzer.englishQuizz.validator.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface UniqueEmail {

    public String message() default "The user with this email has already exist!";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
