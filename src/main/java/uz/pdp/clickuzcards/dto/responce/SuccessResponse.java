package uz.pdp.clickuzcards.dto.responce;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SuccessResponse {
    private String message;
    private LocalDateTime time = LocalDateTime.now();
    public SuccessResponse(){
        this.message = "Successfully";
    }
    public SuccessResponse(String message){
        this.message = message;
    }
}
