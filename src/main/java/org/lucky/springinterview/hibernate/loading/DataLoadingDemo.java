package org.lucky.springinterview.hibernate.loading;


import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Optional;

@SpringBootApplication
@Slf4j
public class DataLoadingDemo implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private Service service;

    public static void main(String[] args) {
        new SpringApplicationBuilder(DataLoadingDemo.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void process(){
        Address address = new Address();
        address.setAddress("pune");

        Person person = new Person();
//        person.setName("foo");
        person.setAddress(address);

        Person save = personRepository.save(person);

        /*Optional<Person> byId = personRepository.findById(save.getPerson_id());
        log.info("Person data >> "+byId);
        log.info("Address data >> "+byId.get().getAddress());*/

        Person person1 = service.getPerson();
        log.info("Person data >> "+person1);
        log.info("Address data >> "+person1.getAddress());
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Loading demo application started...!");
        process();
    }
}
