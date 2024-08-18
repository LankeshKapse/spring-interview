package org.lucky.springinterview.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
                                    DataSourceTransactionManagerAutoConfiguration.class,
                                    HibernateJpaAutoConfiguration.class})
public class ConfigDemo implements CommandLineRunner {

    @Autowired
    private CustomeConfig config;

    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigDemo.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("running");
        System.out.println(config);
    }
}
