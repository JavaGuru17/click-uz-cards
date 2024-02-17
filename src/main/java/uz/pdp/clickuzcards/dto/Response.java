package uz.pdp.clickuzcards.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Response {
    private final LocalDate timestamp = LocalDate.now();
    private final String message;

    public Response(String message) {
        this.message = message;
    }
}
