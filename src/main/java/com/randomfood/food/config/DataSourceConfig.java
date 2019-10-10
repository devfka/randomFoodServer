package com.randomfood.food.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Autowired
    Environment environment;

    @Bean
    @Primary
//    @Profile("dev")
    public DataSource datasource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dataSourceBuilder.url(environment.getProperty("spring.datasource.url"));
//        dataSourceBuilder.username("SA");
//        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }

}
