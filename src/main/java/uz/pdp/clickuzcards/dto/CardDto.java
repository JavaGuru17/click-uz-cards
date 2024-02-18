package uz.pdp.clickuzcards.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import uz.pdp.clickuzcards.model.Card;
import uz.pdp.clickuzcards.model.enums.CardType;
import uz.pdp.clickuzcards.util.annotations.Balance;
import uz.pdp.clickuzcards.util.annotations.CardNumber;
import uz.pdp.clickuzcards.util.annotations.Cvv;
import uz.pdp.clickuzcards.util.annotations.ExpiryDate;
import uz.pdp.clickuzcards.util.annotations.Length;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class CardDto {
    private Long id;
    @Length(fieldName = "card name", min = 0, max = 30)
    private String name;
    @CardNumber
    private String cardNumber;
    @ExpiryDate
    private String expiryDate;
    @Enumerated(EnumType.STRING)
    private CardType cardType;
    private Boolean isMain;
    @Cvv
    private String cvv;
    @Balance
    private BigDecimal balance;
    public CardDto(Card card) {
        this.id = card.getId();
        this.name = card.getName();
        this.cardNumber = card.getCardNumber();
        this.expiryDate = card.getExpiryDate();
        this.cardType = card.getCardType();
        this.isMain = card.getIsMain();
        this.cvv = card.getCvv();
        this.balance = card.getBalance();
    }
}
