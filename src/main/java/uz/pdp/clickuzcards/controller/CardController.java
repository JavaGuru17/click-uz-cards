package uz.pdp.clickuzcards.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.clickuzcards.dto.AddCardDto;
import uz.pdp.clickuzcards.dto.CardDto;
import uz.pdp.clickuzcards.dto.SetBalanceDto;
import uz.pdp.clickuzcards.dto.responce.CustomResponseEntity;
import uz.pdp.clickuzcards.dto.responce.Response;
import uz.pdp.clickuzcards.service.CardService;

import java.util.List;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;
    @PostMapping("/create")
    public CustomResponseEntity<?> createCard(@RequestBody AddCardDto addCardDto) {
        return CustomResponseEntity.ok(cardService.create(addCardDto));
    }
    @PatchMapping("/update")
    public CustomResponseEntity<?> updateCard(@RequestBody CardDto cardDto) {
        return CustomResponseEntity.ok(cardService.update(cardDto));
    }
    @DeleteMapping("/{id}")
    public CustomResponseEntity<?> delete(@PathVariable Long id) {
        cardService.delete(id);
        return CustomResponseEntity.ok(new Response("Card deleted"));
    }
    @GetMapping("/{id}")
    public CustomResponseEntity<CardDto> getById(@PathVariable Long id) {
        return CustomResponseEntity.ok(cardService.getById(id));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public CustomResponseEntity<List<CardDto>> getAll() {
        return CustomResponseEntity.ok(cardService.getAll());
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/all/expiryDate/{expiryDate}")
    public CustomResponseEntity<List<CardDto>> getAllByExpiryDate(@PathVariable String expiryDate) {
        return CustomResponseEntity.ok(cardService.getAllByExpiryDate(expiryDate));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/all/cardType/{cardType}")
    public CustomResponseEntity<List<CardDto>> getAllByCardType(@PathVariable String cardType) {
        return CustomResponseEntity.ok(cardService.getAllByCardType(cardType));
    }
    @GetMapping("/cardNumber/{cardNumber}")
    public CustomResponseEntity<CardDto> getByCardNumber(@PathVariable String cardNumber) {
        return CustomResponseEntity.ok(cardService.getByCardNumber(cardNumber));
    }
    @PostMapping("/balance")
    public CustomResponseEntity<?> setBalance(@RequestBody SetBalanceDto setBalanceDto) {
        cardService.setBalance(setBalanceDto);
        return CustomResponseEntity.ok(new Response("Balance set"));
    }
}
