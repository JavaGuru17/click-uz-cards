package uz.pdp.clickuzcards.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.clickuzcards.dto.AddCardDto;
import uz.pdp.clickuzcards.dto.CardDto;
import uz.pdp.clickuzcards.dto.SetBalanceDto;
import uz.pdp.clickuzcards.exception.AlreadyExistsException;
import uz.pdp.clickuzcards.exception.InvalidArgumentException;
import uz.pdp.clickuzcards.exception.NotFoundException;
import uz.pdp.clickuzcards.exception.NullOrEmptyException;
import uz.pdp.clickuzcards.model.Card;
import uz.pdp.clickuzcards.model.enums.CardType;
import uz.pdp.clickuzcards.repository.CardRepository;
import uz.pdp.clickuzcards.service.CardService;
import uz.pdp.clickuzcards.util.Validator;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    @Override
    public CardDto create(AddCardDto addCardDto) {
        if (addCardDto == null)
            throw new NotFoundException("Card");
        if (addCardDto.getId() != null) {
            if (cardRepository.findById(addCardDto.getId()).isPresent())
                throw new AlreadyExistsException("This card id");
        }
        if (Validator.isNullOrEmpty(addCardDto.getCardNumber()))
            throw new NullOrEmptyException("Card number");
        if (addCardDto.getCardNumber() != null){
            if (cardRepository.findByCardNumber(addCardDto.getCardNumber()).isPresent())
                throw new AlreadyExistsException("This card number");
        }
        if (addCardDto.getCardType() == null)
            throw new NullOrEmptyException("Card Type");
        if (addCardDto.getCardType().equals(CardType.VISA)) {
            if (addCardDto.getCvv() == null)
                throw new NullOrEmptyException("Card cvv");
        } else {
            if (addCardDto.getCvv() != null)
                throw new InvalidArgumentException("Card cvv");
        }
        return new CardDto(cardRepository.save(convertToEntity(addCardDto)));
    }

    @Override
    public CardDto update(CardDto cardDto) {
        if (cardDto == null)
            throw new NotFoundException("Card");
        if (cardDto.getId() == null)
            throw new NullOrEmptyException("Card id");
        Card existingCard = cardRepository.findById(cardDto.getId())
                .orElseThrow(() -> new NotFoundException("Card"));
        Boolean isMain = cardDto.getIsMain() == null ? existingCard.getIsMain() : cardDto.getIsMain();
        existingCard.setName(Validator.requireNonNullElse(cardDto.getName(),existingCard.getName()));
        existingCard.setIsMain(isMain == existingCard.getIsMain() ? existingCard.getIsMain() : isMain);
        return new CardDto(cardRepository.save(existingCard));
    }

    @Override
    public void delete(Long id) {
        if (id == null)
            throw new NullOrEmptyException("Card id");

        cardRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Card"));

        cardRepository.deleteById(id);
    }

    @Override
    public CardDto getById(Long id) {
        if (id == null)
            throw new NullOrEmptyException("Card id");

        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Card"));

        return new CardDto(card);
    }

    @Override
    public List<CardDto> getAll() {
        return cardRepository.findAll().stream().map(CardDto::new).toList();
    }

    @Override
    public List<CardDto> getAllByExpiryDate(String expiryDate) {
        if (expiryDate == null)
            throw new NullOrEmptyException("Expiry date");
        return cardRepository.findAllByExpiryDate(expiryDate).stream().map(CardDto::new).toList();
    }

    @Override
    public List<CardDto> getAllByCardType(String cardType) {
        if (Validator.isNullOrEmpty(cardType))
            throw new NullOrEmptyException("Card type");
        return cardRepository.findAllByCardType(CardType.valueOf(cardType.toUpperCase())).stream().map(CardDto::new).toList();
    }

    @Override
    public CardDto getByCardNumber(String cardNumber) {
        if (cardNumber == null)
            throw new NullOrEmptyException("Card number");

        Card card = cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new NotFoundException("Card"));

        return new CardDto(card);
    }

    @Override
    public void setBalance(SetBalanceDto setBalanceDto) {
        if (setBalanceDto.getBalance() == null)
            throw new NullOrEmptyException("Balance");
        if (setBalanceDto.getBalance().compareTo(BigDecimal.ZERO) < 0)
            throw new InvalidArgumentException("Balance");
        Card card = cardRepository.findById(setBalanceDto.getCardId())
                .orElseThrow(() -> new NotFoundException("Card id"));
        card.setBalance(setBalanceDto.getBalance());
        cardRepository.save(card);
    }

    private Card convertToEntity(AddCardDto addCardDto) {
        return Card.builder()
                .id(addCardDto.getId())
                .name(addCardDto.getName())
                .cardNumber(addCardDto.getCardNumber())
                .expiryDate(addCardDto.getExpiryDate())
                .cardType(addCardDto.getCardType())
                .isMain(addCardDto.getIsMain() != null && addCardDto.getIsMain())
                .balance(new BigDecimal(300000))
                .cvv(addCardDto.getCvv())
                .build();
    }
}

