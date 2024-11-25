package com.example.demo.domain.repository;

import com.example.demo.domain.entity.Book;
import com.example.demo.domain.entity.Lend;
import com.example.demo.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LendRepositoryTest {

    @Autowired
    private LendRepository lendRepository;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void t1(){
        User user1 =  userRepository.findById("aaa").get();
        Book book1 = bookRepository.findByBookNameAndIsbn("book1","112");

        Lend lend = Lend.builder()
                .lendDate(null)
                .returnDate(null)
                .user(user1)
                .book(book1)
                .build();
        lendRepository.save(lend);
    }

    @Test
    public void t2(){
//        Lend lend =  lendRepository.findById(1L).get();
//        System.out.println(lend.getId());
//        System.out.println(lend.getBook());

        List<Lend> lends =  lendRepository.findLendsByUser("aaa");
        lends.forEach(l->{
            System.out.println(l.getUser());
        });

    }

    @Test
    public void t3(){
        List<Lend> lends =  lendRepository.findLendsByUserAndBook();
        lends.forEach(System.out::println);
    }
}