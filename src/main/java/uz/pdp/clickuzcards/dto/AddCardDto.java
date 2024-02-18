package uz.pdp.clickuzcards.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import uz.pdp.clickuzcards.model.enums.CardType;
import uz.pdp.clickuzcards.util.annotations.CardNumber;
import uz.pdp.clickuzcards.util.annotations.Cvv;
import uz.pdp.clickuzcards.util.annotations.ExpiryDate;
import uz.pdp.clickuzcards.util.annotations.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class AddCardDto {
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
}
