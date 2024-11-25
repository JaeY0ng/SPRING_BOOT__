package com.example.demo.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DataSourceConfigTest {

//    @Autowired
//    private DataSource dataSource;
//
//
//    @Test
//    public void t1() throws SQLException {
//        System.out.println(dataSource);
//
//        Connection conn =  dataSource.getConnection();
//        PreparedStatement pstmt =
//        conn.prepareStatement("insert into tbl_memo values(9877,'aa','ww',now())");
//        pstmt.executeUpdate();
//    }
    @Autowired
    private ApplicationContext applicationContext;
    @Test
    public void t2(){
        String beanName = "dataSource3";
        DataSource dataSource = applicationContext.getBean(beanName, DataSource.class);
        assertNotNull(dataSource);
    }
}