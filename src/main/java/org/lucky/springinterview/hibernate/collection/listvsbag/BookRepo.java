package org.lucky.springinterview.hibernate.collection.listvsbag;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book,Integer> {
}
