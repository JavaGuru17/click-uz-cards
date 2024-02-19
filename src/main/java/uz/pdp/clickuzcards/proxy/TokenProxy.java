package uz.pdp.clickuzcards.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.clickuzcards.dto.responce.CustomResponseEntity;
import uz.pdp.clickuzcards.security.ClickUzAuthentication;

@FeignClient(name = "click-uz-settings")
public interface TokenProxy {
    @GetMapping("/verify")
    CustomResponseEntity<ClickUzAuthentication> verify(@RequestParam String token);
}
