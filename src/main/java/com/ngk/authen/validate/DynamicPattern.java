package com.ngk.authen.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DynamicPatternValidator.class)
public @interface DynamicPattern {
    String message() default "Định dạng không hợp lệ từ DB";
    String key();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}