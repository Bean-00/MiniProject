package net.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MiniApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MiniApplication.class);
        application.run(args);

    }
}
