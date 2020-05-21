package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.db.repository.TestMapper;
import com.example.dto.TestDto;

@Service
public class TestService {
	@Autowired
	private TestMapper mapper;
	
	public List<TestDto> selectTest() {
		return mapper.selectTest();
	}

}
