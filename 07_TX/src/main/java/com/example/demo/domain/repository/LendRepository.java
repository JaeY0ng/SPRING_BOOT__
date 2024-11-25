package com.example.demo.domain.repository;


import com.example.demo.domain.entity.Lend;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LendRepository extends JpaRepository<Lend,Long> {

    @Query("SELECT l FROM Lend AS l JOIN FETCH l.user  where l.user.username = :username")
    List<Lend> findLendsByUser(@Param("username") String username);

    @Query("SELECT l FROM Lend AS l JOIN FETCH l.user JOIN FETCH l.book")
    List<Lend> findLendsByUserAndBook();
}










