package uz.pdp.clickuzcards.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.clickuzcards.security.ClickUzAuthentication;

@FeignClient
public interface TokenProxy {
    @GetMapping("/verify")
    ResponseEntity<ClickUzAuthentication> verify(@RequestParam String token);
}
