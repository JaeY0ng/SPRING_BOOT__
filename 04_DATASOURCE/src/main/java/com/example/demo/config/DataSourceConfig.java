package com.example.demo.config;

import javax.sql.DataSource;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;



@Configuration
public class DataSourceConfig {
	
	
	@Bean(name = "dataSource2")
	public DataSource dataSource2() {
		
		/* DriverManagerDataSource dataSource = new DriverManagerDataSource(); */
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/bookdb");
		dataSource.setUsername("root");
		dataSource.setPassword("1234");
		
		dataSource.setInitialSize(5);//초기 연결개수
		dataSource.setMaxTotal(10);//최대 연결 개수
		dataSource.setMaxIdle(8);//최대 유휴 연결 수
		dataSource.setMinIdle(3);//최소 유휴 연결 수
		dataSource.setMaxWaitMillis(20000);//CP에서 Connection객체를 가져오기 위해 기다리는 최대시간
		return dataSource;
	}
	@Bean
	public DataSource dataSource3() {

		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/bookdb");
		dataSource.setUsername("root");
		dataSource.setPassword("1234");
		
		dataSource.setMaximumPoolSize(10);
		

		return dataSource;
	}
	
}
