package com.example.demo.domain.repository;

import com.example.demo.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


//명명규칙 참고 https://priming.tistory.com/114
@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
       //조건절 일치(=)
       List<Book> findByBookName(String bookName);
       List<Book> findByPublisher(String publisher);
       List<Book> findByIsbn(String isbn);

       Book findByBookNameAndIsbn(String bookName, String isbn);

       //조건절 포함문자열(%)
       List<Book> findByBookNameContains(String keyword);

       //행의개수 받기
       int countByBookName(String bookname);
       //포함문자열에 대한 행의개수 받기
       int countByBookNameContaining(String keyword);

       //책이름으로 삭제
       void deleteByBookName(String bookname);


//명명규칙 참고
// 07 JPA 명명규칙 확인 ------------------------------
//    // 책 이름으로 책 검색
//    List<Book> findByBookname(String bookname);
//    // 출판사로 책 검색
//    List<Book> findByPublisher(String publisher);
//    // ISBN으로 책 검색
//    Book findByIsbn(String isbn);
//    // 책 코드 범위로 책 검색
//    List<Book> findByBookcodeBetween(long start, long end);
//
//    // 책 이름이나 출판사로 책 검색
//    List<Book> findByBooknameOrPublisher(String bookname, String publisher);
//
//    // 책 이름이나 출판사로 책 검색, 정렬하여 가져오기
//    List<Book> findByBooknameOrPublisherOrderByBooknameAsc(String bookname, String publisher);
//
//    // 책 이름에 특정 문자열이 포함된 책 검색
//    List<Book> findByBooknameContaining(String keyword);
//
//    // 출판사가 특정 문자열로 시작하는 책 검색
//    List<Book> findByPublisherStartingWith(String prefix);
//
//    // 책 이름으로 책 개수 가져오기
//    int countByBookname(String bookname);
//
//    // 출판사로 책 개수 가져오기
//    int countByPublisher(String publisher);
//
//    // 책 이름으로 책 삭제
//    void deleteByBookname(String bookname);
// 07 JPA 명명규칙 확인  끝-----------------------------

}
