package com.example.demo.domain.dao;

import java.sql.SQLException;

import com.example.demo.domain.dto.MemoDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository	
public class MemoDaoImpl {

//	@Autowired
//	private DataSource dataSource3;
//	
//	public int insert(MemoDto memoDto) throws SQLException {
//		//tbl_memo에 insert처리 후 증가 행 반환
//		Connection conn = dataSource3.getConnection();
//		PreparedStatement pstmt = conn.prepareStatement("insert into tbl_memo values(null,?,?,now())");
//		pstmt.setString(1, memoDto.getText());
//		pstmt.setString(2, memoDto.getWriter());
//		
//		int result = pstmt.executeUpdate();
//		
//		pstmt.close();
//		
//		return result;
//	}
	
	@Autowired
	private SqlSession sqlSession;
	private static String namespace = "com.example.demo.domain.mapper.MemoMapper.";
	
	public int insert(MemoDto memoDto) throws SQLException{
		sqlSession.insert(namespace+"Insert",memoDto);
		return memoDto.getId();
	}
	
}







