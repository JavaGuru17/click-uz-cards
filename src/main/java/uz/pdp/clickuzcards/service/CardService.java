package uz.pdp.clickuzcards.service;

import org.springframework.stereotype.Service;
import uz.pdp.clickuzcards.dto.AddCardDto;
import uz.pdp.clickuzcards.dto.CardDto;
import uz.pdp.clickuzcards.model.Card;
import uz.pdp.clickuzcards.model.enums.CardType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public interface CardService {
    Card create(AddCardDto addCardDto);
    Card update(CardDto cardDto);
    void delete(Long id);
    CardDto getById(Long id);
    List<CardDto> getAll();
    List<CardDto> getAllByExpiryDate(LocalDate expiryDate);
    List<CardDto> getAllByCardType(CardType cardType);
    CardDto getByCardNumber(String cardNumber);
    void setBalance(Long id, BigDecimal balance);
}
