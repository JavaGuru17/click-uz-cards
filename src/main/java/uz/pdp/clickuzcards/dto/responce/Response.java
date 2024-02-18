package uz.pdp.clickuzcards.dto.responce;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Response {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final String message;
    public Response(){
        this.message = "Successfully";
    }

    public Response(String message) {
        this.message = message;
    }
}
