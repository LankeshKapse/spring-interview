package org.lucky.springinterview.hibernate.loading;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
@Transactional
public class Service {

    @Autowired
    private PersonRepository personRepository;
    public Person getPerson(){
        return personRepository.getPerson(1);
    }
}
