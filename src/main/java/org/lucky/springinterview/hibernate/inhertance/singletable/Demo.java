package org.lucky.springinterview.hibernate.inhertance.singletable;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@Slf4j
public class Demo implements CommandLineRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Demo.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Override
    public void run(String... args) throws Exception {
      log.info("From Demo run method....!");
    }
}
