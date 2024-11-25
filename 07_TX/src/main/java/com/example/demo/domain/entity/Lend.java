package com.example.demo.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="lend")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lend {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //USER (1-N) LEND

    @ManyToOne(fetch = FetchType.LAZY)
    //@ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "username", foreignKey = @ForeignKey(name="FK_LEND_USER",
    foreignKeyDefinition = "FOREIGN KEY(username) REFERENCES user(username) ON DELETE CASCADE ON UPDATE CASCADE" ))
    private User user;

    //BOOK (1-N) LEND
    @ManyToOne(fetch = FetchType.LAZY)
    //@ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "bookcode", foreignKey = @ForeignKey(name="FK_LEND_BOOK",
            foreignKeyDefinition = "FOREIGN KEY(bookcode) REFERENCES book(bookcode) ON DELETE CASCADE ON UPDATE CASCADE" ))
    private Book book;

    @Column(name="lenddate")
    private LocalDate lendDate;
    @Column(name="returndate")
    private LocalDate returnDate;

}
