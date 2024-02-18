package uz.pdp.clickuzcards.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.clickuzcards.dto.AddCardDto;
import uz.pdp.clickuzcards.dto.CardDto;
import uz.pdp.clickuzcards.dto.SetBalanceDto;
import uz.pdp.clickuzcards.dto.responce.Response;
import uz.pdp.clickuzcards.model.enums.CardType;
import uz.pdp.clickuzcards.service.CardService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;
    @PostMapping("/create")
    public ResponseEntity<?> createCard(@RequestBody AddCardDto addCardDto) {
        return ResponseEntity.ok(cardService.create(addCardDto));
    }
    @PatchMapping("/update")
    public ResponseEntity<?> updateCard(@RequestBody CardDto cardDto) {
        return ResponseEntity.ok(cardService.update(cardDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        cardService.delete(id);
        return ResponseEntity.ok(new Response("Card deleted"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CardDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.getById(id));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<CardDto>> getAll() {
        return ResponseEntity.ok(cardService.getAll());
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/all/expiryDate/{expiryDate}")
    public ResponseEntity<List<CardDto>> getAllByExpiryDate(@PathVariable LocalDate expiryDate) {
        return ResponseEntity.ok(cardService.getAllByExpiryDate(expiryDate));
    }
    //todo write logic for card type in string
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/all/cardType/{cardType}")
    public ResponseEntity<List<CardDto>> getAllByExpiryDate(@PathVariable CardType cardType) {
        return ResponseEntity.ok(cardService.getAllByCardType(cardType));
    }
    @GetMapping("/cardNumber/{cardNumber}")
    public ResponseEntity<CardDto> getByCardNumber(@PathVariable String cardNumber) {
        return ResponseEntity.ok(cardService.getByCardNumber(cardNumber));
    }
    //todo add SetBalanceDto
    @PostMapping("/balance")
    public ResponseEntity<?> setBalance(@RequestBody SetBalanceDto setBalanceDto) {
        cardService.setBalance(setBalanceDto);
        return ResponseEntity.ok(new Response("Balance set"));
    }
}
