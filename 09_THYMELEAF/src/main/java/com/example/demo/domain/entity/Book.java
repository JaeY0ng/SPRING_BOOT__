package com.example.demo.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="book")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name="bookcode")
    private Long bookCode;
    @Column(name="bookname" ,length = 1024)
    private String bookName;
    @Column(name="publisher")
    private String publisher;
    @Column(name="author")
    private String author;
    @Column(name="isbn")
    private String isbn;
}









