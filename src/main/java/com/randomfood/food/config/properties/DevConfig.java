package com.randomfood.food.config.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class DevConfig implements ConfigInterface{

    @Autowired
    private Environment env;

    @Override
    public void showMessage() {
        System.out.println("Development configuration setup : " + env.getProperty("spring.application.name"));
        System.out.println(env.getProperty("email"));
        System.out.println(env.getProperty("spring.datasource.url"));
        System.out.println(env.getProperty("spring.datasource.jndi-name"));
    }

}
