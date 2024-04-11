package org.lucky.springinterview.hibernate.collection.listvsbag;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
@Slf4j
public class ListVsBagDemo implements CommandLineRunner {

    @Autowired
    private EntityManager em;
    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AutherRepo autherRepo;
    public static void main(String[] args) {
        new SpringApplicationBuilder(ListVsBagDemo.class)
//                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void saveBooks(){
        Book book1 = new Book();
        book1.setName("book3");

        Book book2 = new Book();
        book2.setName("book3");

        Book book3 = new Book();
        book3.setName("book3");

        bookRepo.save(book1);
        bookRepo.save(book2);
        bookRepo.save(book3);
        ArrayList bookList = new ArrayList(Arrays.asList(book1,book2,book3));

        Auther auther =new Auther();
        auther.setBookList(bookList);
        auther.setName("auther1");

        autherRepo.save(auther);

        auther.getBookList().remove(book1);

        auther.setIdList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        auther.setIdList2(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        autherRepo.save(auther);

        autherRepo.findAll().stream()
                .flatMap(a -> a.getBookList().stream())
                .forEach(System.out::println);

        autherRepo.findAll().stream().forEach(System.out::println);

        return;

    }

    @Override
    public void run(String... args) throws Exception {
        log.info("From List vs Bag collection Demo");
        saveBooks();
    }
}
