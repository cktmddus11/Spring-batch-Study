package com.example.db.repository;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.dto.TestDto;

@Repository
@Mapper
public interface TestMapper {
	List<TestDto> selectTest();
}
