package Lab4.service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IntSizeValidator implements ConstraintValidator<IntSize, Integer> {
    private int minAnnotation;
    private int maxAnnotation;

    @Override
    public void initialize(IntSize intSize) {
        this.minAnnotation = intSize.min();
        this.maxAnnotation = intSize.max();
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
