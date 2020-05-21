package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.dto.TestDto;
import com.example.service.TestService;

@Controller
@RequestMapping("*")
public class TestController {
	
	@Autowired
	private TestService testService;

	@RequestMapping(method=RequestMethod.GET, value="/test")
	public ModelAndView test() {
		ModelAndView mav = new ModelAndView();
		List<TestDto> testList = testService.selectTest();
		mav.addObject("list", testList);
		return mav;
	}
}
