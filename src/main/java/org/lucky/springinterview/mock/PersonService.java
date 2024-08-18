package org.lucky.springinterview.mock;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PersonService {

    private PersonRepo repo;

    public String savePerson(Object o){
        return repo.save(o);
    }
}
