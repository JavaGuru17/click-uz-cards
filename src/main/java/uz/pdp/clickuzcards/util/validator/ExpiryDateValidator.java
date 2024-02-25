package uz.pdp.clickuzcards.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.clickuzcards.exception.InvalidArgumentException;
import uz.pdp.clickuzcards.util.annotations.ExpiryDate;

import java.time.LocalDate;

public class ExpiryDateValidator implements ConstraintValidator<ExpiryDate, String> {
    @Override
    public boolean isValid(String date, ConstraintValidatorContext context) {
        if (date == null)
            return true;
        if (!date.matches("(0[1-9]|1[0-2])/[0-9]{2}"))
            throw new InvalidArgumentException("Expiry date");
        String[] split = date.split("/");
        if (LocalDate.now().getYear() > Integer.parseInt("20" + split[1]))
            throw new InvalidArgumentException("Expiry date");
        return true;
    }
}