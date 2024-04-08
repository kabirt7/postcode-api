package io.nology.postcodeapi.postcodedata;

import java.util.Set;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SuburbSetValidator implements ConstraintValidator<ValidSuburbName, Set<SuburbEntity>> {

    @Override
    public void initialize(ValidSuburbName constraintAnnotation) {
    }

    @Override
    public boolean isValid(Set<SuburbEntity> suburbs, ConstraintValidatorContext context) {
        if (suburbs == null || suburbs.isEmpty()) {
            return false; 
        }

        for (SuburbEntity suburb : suburbs) {
            if (suburb == null || suburb.getSuburbName() == null || suburb.getSuburbName().isEmpty()) {
                return false; 
            }

            if (!suburb.getSuburbName().matches("[a-zA-Z\\-]+")) {
                return false; 
            }
        }

        return true;
    }
}
