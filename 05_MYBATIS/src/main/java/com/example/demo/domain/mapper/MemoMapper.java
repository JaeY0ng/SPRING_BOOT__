package com.example.demo.domain.mapper;

import java.util.List;
import java.util.Map;

import com.example.demo.domain.dto.MemoDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface MemoMapper {
	
	//Annotaion 을 이용한 Mapping
	
	@SelectKey(statement = "select max(id) from tbl_memo",keyProperty = "id",before = false,resultType = int.class)
	@Insert(value="insert into tbl_memo values(null,#{text},#{writer},now())")
	public int Insert(MemoDto memoDto);
	
	@Update(value="update tbl_memo set text=#{text},writer=#{writer} where id=#{id}")
	public int Update(MemoDto memoDto);

	@Delete(value="delete from tbl_memo where id=#{id}")
	public int Delete(@Param("id") int tmp);
	
	@Select(value="select * from tbl_memo where id=#{id}")
	public MemoDto SelectOne(@Param("id") int id);
	
	@Select(value="select * from tbl_memo")
	public List<MemoDto> SelectAll();
	
	@Results(id="MemoResultMap",value={
			@Result(property = "id",column = "id"),
			@Result(property = "text",column = "text"),
	})
	@Select(value="select id,text from tbl_memo")
	public List< Map<String,Object>  > SelectAllByResultMap();
	
	
	//Xml 을 이용한 Mapping
	public int Insert_xml(MemoDto memoDto);
	public int Update_xml(MemoDto memoDto);
	public int Delete_xml(@Param("id") int id);
	public MemoDto SelectOne_xml(@Param("id") int id);
	public List<MemoDto> SelectAll_xml_1();
	public List< Map<String,Object> > SelectAll_xml_2();
	public List< Map<String,Object> > SelectAll_xml_3();
	public List< Map<String,Object> > Select_if_xml( Map<String,Object> param);
	public List< Map<String,Object> > Select_when_xml( Map<String,Object> param);
	
}






