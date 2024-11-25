package com.example.demo.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;



@Configuration
public class MybatisConfig {
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		
		SqlSessionFactoryBean sessoionFactory = new SqlSessionFactoryBean();
		sessoionFactory.setDataSource(dataSource);
		
		//xml경로설정
		//MemoMapper xml 파일 위치 지정
	    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	    Resource[] resources = resolver.getResources("classpath*:mapper/*.xml");
	    sessoionFactory.setMapperLocations(resources);	
	    
		return sessoionFactory.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate( sqlSessionFactory() );
	}

	
	
}
