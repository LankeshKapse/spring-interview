package org.lucky.springinterview.mock;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;


class PersonServiceTest {

    @Test
    void savePerson() {
        PersonRepo mockRepo = Mockito.mock(PersonRepo.class);
//        PersonRepo repo = new PersonRepo();
        PersonService service = new PersonService();
        service.setRepo(mockRepo);
        Mockito.when(mockRepo.save("person")).thenReturn("return from mock");
        System.out.println(service.savePerson("person"));

    }
}