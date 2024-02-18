package uz.pdp.clickuzcards.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class SetBalanceDto {
    private Long cardId;
    private BigDecimal balance;
}
