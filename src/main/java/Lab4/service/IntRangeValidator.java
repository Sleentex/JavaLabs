package Lab4.service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IntRangeValidator implements ConstraintValidator<IntRange, Integer> {
    private int minAnnotation;
    private int maxAnnotation;

    @Override
    public void initialize(IntRange intRange) {
        this.minAnnotation = intRange.min();
        this.maxAnnotation = intRange.max();
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintContext) {
        if(integer == null)
            return true;

        boolean isValid =  integer >= minAnnotation && integer <= maxAnnotation;

        if(!isValid) {
            constraintContext.disableDefaultConstraintViolation();
            constraintContext.buildConstraintViolationWithTemplate("must be between " + minAnnotation + " and " + maxAnnotation)
            .addConstraintViolation();
        }

        return isValid;
    }
}
