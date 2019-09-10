package com.yi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yi.domain.Creiteria;
import com.yi.domain.PageMaker;
import com.yi.domain.ReplyVO;
import com.yi.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyContorller {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ReplyService service;
	
	
	// /replies/all/24042 주소를 변수로 받기
	@RequestMapping(value="/all/{bno}",method=RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") int bno){
		logger.info("all/bno----------------bno="+bno);
		ResponseEntity<List<ReplyVO>> entity = null;
		
		try {
			List<ReplyVO> list = service.list(bno);
			entity = new ResponseEntity<>(list,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		
		return entity;
	}
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public ResponseEntity<String> create(@RequestBody ReplyVO vo){
		ResponseEntity<String> entity = null;
		logger.info("create------------vo="+vo);
		
		try {
			service.create(vo);
			entity = new ResponseEntity<>("success",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	//POST,PUT 데이터가 body에 실려서 옴. 따라서, @requsetbody를 사용해야지만, 정상적으로 값을 받을수 있음
	@RequestMapping(value="{rno}",method=RequestMethod.PUT)
	public ResponseEntity<String> update(@PathVariable("rno") int rno,@RequestBody ReplyVO vo){
		logger.info("update-------------rno"+rno+",vo="+vo);
		ResponseEntity<String> entity = null;
		vo.setRno(rno);
		try {
			service.update(vo);
			entity=new ResponseEntity<String>("success",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
		}
		
		
		return entity;
		
	}
	
	
	@RequestMapping(value="{rno}",method=RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("rno") int rno){
		ResponseEntity<String> entity = null;
		logger.info("delete-------------- rno:"+rno);
		
		try {
			service.delete(rno);
			entity = new ResponseEntity<String>("success",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
		}
		
		
		return entity;
	}
	
	// List<ReplyVO>,PageMaker
	@RequestMapping(value="/{bno}/{page}",method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listPage(@PathVariable("bno") int bno,@PathVariable("page") int page){
		ResponseEntity<Map<String,Object>> entity = null;
		logger.info("listPage--------------------bno="+bno+",page="+page);
		Creiteria cri = new Creiteria();
		cri.setPage(page);
		try {
			List<ReplyVO> list = service.listPage(bno, cri);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			
			pageMaker.setTotalCount(service.totalCount(bno));
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", list);
			map.put("pageMaker", pageMaker);
			
			entity = new ResponseEntity<>(map,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
		
	}
	
	
}









