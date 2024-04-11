package org.lucky.springinterview.hibernate.collection.listvsbag;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

public interface AutherRepo extends JpaRepository<Auther,Integer> {

    @Query("from Auther")
    @Transactional(Transactional.TxType.REQUIRED)
    Stream<Auther> findAllStream();
}
