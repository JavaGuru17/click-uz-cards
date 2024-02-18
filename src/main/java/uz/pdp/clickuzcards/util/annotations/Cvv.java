package uz.pdp.clickuzcards.util.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import uz.pdp.clickuzcards.util.validator.CvvValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CvvValidator.class)
public @interface Cvv {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
