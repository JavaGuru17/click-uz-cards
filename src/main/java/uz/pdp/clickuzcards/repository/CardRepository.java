package uz.pdp.clickuzcards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.clickuzcards.model.Card;
import uz.pdp.clickuzcards.model.enums.CardType;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findByCardNumber(String cardNumber);
    List<Card> findAllByExpiryDate(LocalDate localDate);
    List<Card> findAllByCardType(CardType cardType);
}