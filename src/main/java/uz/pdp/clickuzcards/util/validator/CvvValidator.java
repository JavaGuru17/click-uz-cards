package uz.pdp.clickuzcards.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.clickuzcards.exception.InvalidArgumentException;
import uz.pdp.clickuzcards.util.annotations.Cvv;

public class CvvValidator implements ConstraintValidator<Cvv, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null)
            return true;
        if (value.matches("\\d{3}"))
            return true;
        throw new InvalidArgumentException("Cvv");
    }
}
