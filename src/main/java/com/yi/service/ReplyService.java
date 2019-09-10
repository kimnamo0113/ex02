package com.yi.service;

import java.util.List;

import com.yi.domain.Creiteria;
import com.yi.domain.ReplyVO;

public interface ReplyService {
	public List<ReplyVO> list(int bno) throws Exception;
	public void create(ReplyVO vo) throws Exception;
	public void update(ReplyVO vo) throws Exception;
	public void delete(int rno) throws Exception;
	
	public List<ReplyVO> listPage(int bno,Creiteria cri) throws Exception;
	public int totalCount(int bno) throws Exception;
}
