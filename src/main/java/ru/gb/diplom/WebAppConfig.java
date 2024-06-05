package ru.gb.diplom;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Bean
    public SpringDataDialect springDataDialect() {
        return new SpringDataDialect();
    }
}

