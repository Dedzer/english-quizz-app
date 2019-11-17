package com.dedzer.englishQuizz.annotations;



import com.dedzer.englishQuizz.validator.ValidEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface ValidEmail {

    @Constraint(validatedBy = ValidEmailValidator.class)
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
    public @interface UniqueEmail {

        public String message() default "Invalid Email!";

        public Class<?>[] groups() default {};

        public Class<? extends Payload>[] payload() default {};

    }
}
