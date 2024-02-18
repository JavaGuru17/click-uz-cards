package uz.pdp.clickuzcards.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.clickuzcards.util.annotations.Balance;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class SetBalanceDto {
    private Long cardId;
    @Balance
    private BigDecimal balance;
}
