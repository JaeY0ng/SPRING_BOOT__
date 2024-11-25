package com.example.demo.domain.repository;

import com.example.demo.domain.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {
        "logging.level.com.example=error"
})
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void t1(){
        Book book = Book.builder()
                    .bookCode(1L)
                    .bookName("book122")
                    .author("author122")
                    .publisher("publisher1")
                    .isbn("111-222")
                    .build();

        Book result =  bookRepository.save(book);
        assertThat(result).isEqualTo(book);
    }
    @Test
    public void t2(){
        Book book = Book.builder()
                .bookCode(1L)
                .bookName("bookUpdate1")
                .author("authorUpdate1")
                .publisher("publisherUpdate1")
                .isbn("111-222Update")
                .build();

        Book result =  bookRepository.save(book);
        assertThat(result).isEqualTo(book);
    }
    @Test
    public void t3(){
         bookRepository.deleteById(1L);
    }
    @Test
    public void t4(){
        Optional<Book> bookOptional =bookRepository.findById(1L);
        if(bookOptional.isPresent())
            System.out.println(bookOptional.get());
    }
    @Test
    public void t5(){
        List<Book> list =bookRepository.findAll();
        list.forEach(System.out::println);
    }

    @Test
    public void t6(){
        List<Book> list = bookRepository.findByBookName("a");
        list.forEach(System.out::println);
    }
    @Test
    public void t7(){
        List<Book> list = bookRepository.findByBookNameContains("a");
        list.forEach(System.out::println);
    }

}