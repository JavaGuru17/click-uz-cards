package uz.pdp.clickuzcards.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
@Setter
@ToString
@Builder
@Entity
public class Card extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Length(fieldName = "card", min = 0, max = 30)
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
}
