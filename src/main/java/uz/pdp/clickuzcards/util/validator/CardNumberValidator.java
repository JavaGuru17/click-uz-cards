package uz.pdp.clickuzcards.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.clickuzcards.exception.InvalidArgumentException;
import uz.pdp.clickuzcards.util.annotations.CardNumber;

public class CardNumberValidator implements ConstraintValidator<CardNumber, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null)
            return true;
        if (value.matches("\\d{4}-\\d{4}-\\d{4}-\\d{4}"))
            return true;
        throw new InvalidArgumentException("Card number");
    }
}
