package Lesson_1.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"Lesson_1.demo"})
public class UserApplicationConfig {
    @Bean
    public Square square() {
        Square out = new Square(10);
        return out;
    }
}
