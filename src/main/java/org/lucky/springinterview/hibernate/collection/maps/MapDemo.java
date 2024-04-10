package org.lucky.springinterview.hibernate.collection.maps;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Map;

@SpringBootApplication
@Slf4j
public class MapDemo implements CommandLineRunner {

    @Autowired
    private OrderRepo orderRepo;

    public static void main(String[] args) {
        new SpringApplicationBuilder(MapDemo.class)
//                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        Order order =new Order();
        Map<Integer, String> map = Map.of(1, "value1", 2, "value2");
        order.setMapCollection(map);
        orderRepo.save(order);
    }
}
