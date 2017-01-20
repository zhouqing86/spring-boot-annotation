package com.example;

import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class TestConfiguration {

    @Autowired
    Environment environment;

    @Bean
    public String message() {
        return "Hello,World!";
    }

    @Bean
    public User getUser() {
        User user = new User();
        user.setFirstName(environment.getProperty("BEAN_NAME"));
        user.setLastName("LastName");
        return user;
    }
}
