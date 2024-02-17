package uz.pdp.clickuzcards.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public AuditorAware<String> auditorAware(){
        return () -> Optional.of("ismoil_0709");
//        return () -> Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }
}