package uz.pdp.clickuzcards.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import uz.pdp.clickuzcards.model.enums.CardType;
import uz.pdp.clickuzcards.util.annotations.CardNumber;
import uz.pdp.clickuzcards.util.annotations.Length;

import java.time.LocalDate;

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
    private LocalDate expiryDate;
    @Enumerated(EnumType.STRING)
    private CardType cardType;
    private boolean isMain;
    private Short cvv;
}
