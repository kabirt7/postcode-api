package io.nology.postcodeapi.postcodedata;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SuburbSetValidator.class)
@Documented
public @interface ValidSuburbName {
    String message() default "Suburb name must contain only letters or a hyphen";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}