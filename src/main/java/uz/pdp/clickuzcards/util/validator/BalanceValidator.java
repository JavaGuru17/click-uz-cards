package uz.pdp.clickuzcards.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.clickuzcards.exception.InvalidArgumentException;
import uz.pdp.clickuzcards.util.annotations.Balance;

import java.math.BigDecimal;

public class BalanceValidator implements ConstraintValidator<Balance, BigDecimal> {
    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null)
            return true;
        if (value.compareTo(BigDecimal.ZERO) >= 0)
            return true;
        throw new InvalidArgumentException("Balance");
    }
}
