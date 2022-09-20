package com.todo.annotation;

import com.todo.utils.EmailValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidation.class)
public @interface EmailValidate {
  String message() default "Email address in incorrect format";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}