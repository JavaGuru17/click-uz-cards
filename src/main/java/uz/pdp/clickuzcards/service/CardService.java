package uz.pdp.clickuzcards.service;

import org.springframework.stereotype.Service;
import uz.pdp.clickuzcards.dto.AddCardDto;
import uz.pdp.clickuzcards.dto.CardDto;
import uz.pdp.clickuzcards.dto.SetBalanceDto;

import java.util.List;

@Service
public interface CardService {
    CardDto create(AddCardDto addCardDto);
    CardDto update(CardDto cardDto);
    void delete(Long id);
    CardDto getById(Long id);
    List<CardDto> getAll();
    List<CardDto> getAllByExpiryDate(String expiryDate);
    List<CardDto> getAllByCardType(String cardType);
    CardDto getByCardNumber(String cardNumber);
    void setBalance(SetBalanceDto setBalanceDto);
}
