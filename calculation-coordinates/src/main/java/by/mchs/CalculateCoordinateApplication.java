package by.mchs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CalculateCoordinateApplication{

    public static void main(String[] args) {
        SpringApplication.run(CalculateCoordinateApplication.class, args);
    }
}
