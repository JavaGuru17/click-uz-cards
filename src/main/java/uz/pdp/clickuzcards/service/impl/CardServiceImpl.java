package uz.pdp.clickuzcards.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.clickuzcards.dto.AddCardDto;
import uz.pdp.clickuzcards.dto.CardDto;
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
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    @Override
    public Card create(AddCardDto addCardDto) {
        if (addCardDto == null)
            throw new NotFoundException("Card");
        if (addCardDto.getId() != null) {
            if (cardRepository.findById(addCardDto.getId()).isPresent())
                throw new AlreadyExistsException("This card id");
        }
        if (Validator.isNullOrEmpty(addCardDto.getCardNumber()))
            throw new NullOrEmptyException("Card number");
        if (cardRepository.findByCardNumber(addCardDto.getCardNumber()).isPresent())
            throw new AlreadyExistsException("This card number");
        if (addCardDto.getExpiryDate() == null)
            throw new NullOrEmptyException("Card expiry date");
        if (addCardDto.getCardType().equals(CardType.VISA)) {
            if (addCardDto.getCvv() == null)
                throw new NullOrEmptyException("Card cvv");
        } else {
            if (addCardDto.getCvv() != null)
                throw new InvalidArgumentException("Card cvv");
        }

        return cardRepository.save(convertToEntity(addCardDto));
    }

    @Override
    public Card update(CardDto cardDto) {
        if (cardDto == null)
            throw new NotFoundException("Card");
        if (cardDto.getId() == null)
            throw new NullOrEmptyException("Card id");

        Card existingCard = cardRepository.findById(cardDto.getId())
                .orElseThrow(() -> new NotFoundException("Card"));

        boolean updatedIsMain = cardDto.isMain() != existingCard.isMain() ? cardDto.isMain() : existingCard.isMain();

        Card updateCard = Card.builder()
                .id(existingCard.getId())
                .name(Validator.requireNonNullElse(cardDto.getName(), existingCard.getName()))
                .cardNumber(Validator.requireNonNullElse(cardDto.getCardNumber(), existingCard.getCardNumber()))
                .expiryDate(Validator.requireNonNullElse(cardDto.getExpiryDate(), existingCard.getExpiryDate()))
                .cardType(Validator.requireNonNullElse(cardDto.getCardType(), existingCard.getCardType()))
                .cvv(Validator.requireNonNullElse(cardDto.getCvv(), existingCard.getCvv()))
                .balance(Validator.requireNonNullElse(cardDto.getBalance(), existingCard.getBalance()))
                .isMain(updatedIsMain)
                .build();

        cardRepository.save(updateCard);

        return convertToEntity(cardDto);
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
    public List<CardDto> getAllByExpiryDate(LocalDate expiryDate) {
        if (expiryDate == null)
            throw new NullOrEmptyException("Expiry date");
        return cardRepository.findAllByExpiryDate(expiryDate).stream().map(CardDto::new).toList();
    }

    @Override
    public List<CardDto> getAllByCardType(CardType cardType) {
        if (cardType == null)
            throw new NullOrEmptyException("Card type");
        return cardRepository.findAllByCardType(cardType).stream().map(CardDto::new).toList();
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
    public void setBalance(Long id, BigDecimal balance) {
        if (balance == null)
            throw new NullOrEmptyException("Balance");
        if (balance.compareTo(BigDecimal.ZERO) < 0)
            throw new InvalidArgumentException("Balance");

        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Card id"));

        card.setBalance(balance);

        cardRepository.save(card);
    }

    private Card convertToEntity(CardDto cardDto) {
        return Card.builder()
                .id(cardDto.getId())
                .name(cardDto.getName())
                .cardNumber(cardDto.getCardNumber())
                .expiryDate(cardDto.getExpiryDate())
                .cardType(cardDto.getCardType())
                .isMain(cardDto.isMain())
                .cvv(cardDto.getCvv())
                .balance(cardDto.getBalance())
                .build();
    }

    private Card convertToEntity(AddCardDto addCardDto) {
        return Card.builder()
                .id(addCardDto.getId())
                .name(addCardDto.getName())
                .cardNumber(addCardDto.getCardNumber())
                .expiryDate(addCardDto.getExpiryDate())
                .cardType(addCardDto.getCardType())
                .isMain(addCardDto.isMain())
                .cvv(addCardDto.getCvv())
                .build();
    }
}

