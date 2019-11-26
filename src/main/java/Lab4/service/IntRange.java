package Lab4.service;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = IntRangeValidator.class)

public @interface IntRange {
    int min();
    int max();

    String message() default "{Lab4.service.IntSize}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
