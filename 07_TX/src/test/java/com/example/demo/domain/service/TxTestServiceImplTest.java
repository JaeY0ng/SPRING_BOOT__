package com.example.demo.domain.service;

import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TxTestServiceImplTest {

    @Autowired
    TxTestServiceImpl txTestService;

    @Test
    void t1(){
        txTestService.txMapper();
    }

    @Test
    void t2() throws SQLException {
        txTestService.jpaRepository();
    }


}