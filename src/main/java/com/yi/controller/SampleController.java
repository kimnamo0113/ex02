package com.yi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@RequestMapping("/hello2")
	public String sayHello2() {
		return "hello2";
	}
	
	@RequestMapping("/map2")
	public @ResponseBody Map<String,String> getMap(){
		Map<String,String> map = new HashMap<String, String>();
		map.put("key1", "사과");
		map.put("key2", "벌초");
		map.put("key3", "제사");
		map.put("key4", "잠");
		return map;
	}
	
	@RequestMapping("/send")
	public ResponseEntity<Void> sendAuth(){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	@RequestMapping("/sendString")
	public ResponseEntity<String> sendString(){
		return new ResponseEntity<String>("빠꾸빠꾸",HttpStatus.NOT_FOUND);
	}
	@RequestMapping("/sendList")
	public ResponseEntity<List<String>> sendList(){
		List<String> list = new ArrayList<String>();
		list.add("추석");
		list.add("사과");
		list.add("배");
		list.add("귤");
		return new ResponseEntity<>(list,HttpStatus.BAD_REQUEST);
	}
	
	
}












