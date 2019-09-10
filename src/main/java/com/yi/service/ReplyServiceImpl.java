package com.yi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yi.domain.Creiteria;
import com.yi.domain.ReplyVO;
import com.yi.persistence.ReplyDAO;

@Repository
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	ReplyDAO dao;
	
	@Override
	public List<ReplyVO> list(int bno) throws Exception {
		return dao.list(bno);
	}

	@Override
	public void create(ReplyVO vo) throws Exception {
		dao.create(vo);
	}

	@Override
	public void update(ReplyVO vo) throws Exception {
		dao.update(vo);
	}

	@Override
	public void delete(int rno) throws Exception {
		dao.delete(rno);
	}

	@Override
	public List<ReplyVO> listPage(int bno, Creiteria cri) throws Exception {
		return dao.listPage(bno, cri);
	}

	@Override
	public int totalCount(int bno) throws Exception {
		return dao.totalCount(bno);
	}
	
}
