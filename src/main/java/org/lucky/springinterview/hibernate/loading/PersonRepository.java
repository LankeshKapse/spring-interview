package org.lucky.springinterview.hibernate.loading;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("from Person where person_id= :id")
    Person getPerson(int id);
}