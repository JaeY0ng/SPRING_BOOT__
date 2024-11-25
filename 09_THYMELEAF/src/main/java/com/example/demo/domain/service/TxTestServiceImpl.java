package com.example.demo.domain.service;


import com.example.demo.domain.dto.MemoDto;
import com.example.demo.domain.entity.Book;
import com.example.demo.domain.mapper.MemoMapper;
import com.example.demo.domain.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@Slf4j
public class TxTestServiceImpl {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemoMapper memoMapper;


    // MYBATIS EXCEPTION
    @Transactional(rollbackFor = SQLException.class,transactionManager ="dataSourceTransactionManager")
    void txMapper(){
        log.info("txMapper start...");
        MemoDto memoDto = new MemoDto(7777,"a","a");
        memoMapper.InsertTx(memoDto);
        memoMapper.InsertTx(memoDto);
    }

    // JPA EXCEPTION
    @Transactional(rollbackFor = SQLException.class,transactionManager ="jpaTransactionManager")
    void jpaRepository() throws SQLException {
        log.info("jpaRepository start...");

        Book book = new Book(2525L,"a","a","a","1");
        bookRepository.save(book);
        throw new SQLException("FAILED...");

    }

}
