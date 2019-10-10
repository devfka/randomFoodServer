package com.randomfood.food;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class RandomFoodApplication {

    private static final Logger log = LoggerFactory.getLogger(RandomFoodApplication.class);

    @Autowired
    private Environment env;

    public RandomFoodApplication(Environment env) {
        this.env = env;
    }

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(RandomFoodApplication.class);

        Environment env = app.run(args).getEnvironment();

        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\t{http}://localhost:{}\n\t" +
                        "External: \t{http}://{}:{}\n\t" +
                        "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getActiveProfiles());

    }

}
