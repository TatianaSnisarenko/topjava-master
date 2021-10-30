package ru.javawebinar.topjava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.javawebinar.topjava.repository.inmemory.InMemoryMealRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepository;

@Configuration
@ComponentScan({"ru.javawebinar.topjava"})
public class TestConfig {

    @Bean
    public InMemoryMealRepository inMemoryMealRepository(){
        return new InMemoryMealRepository();
    }

    @Bean
    InMemoryUserRepository inMemoryUserRepository(){
        return new InMemoryUserRepository();
    }
}
