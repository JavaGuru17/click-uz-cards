package uz.pdp.clickuzcards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ClickUzCardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClickUzCardsApplication.class, args);
    }

}
