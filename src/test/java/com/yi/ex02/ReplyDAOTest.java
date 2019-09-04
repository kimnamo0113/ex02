package com.yi.ex02;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yi.domain.Creiteria;
import com.yi.domain.ReplyVO;
import com.yi.persistence.ReplyDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ReplyDAOTest {
	
	@Autowired
	ReplyDAO dao;
	
	@Test
	public void test01Create() throws Exception {
		ReplyVO vo = new ReplyVO();
		vo.setBno(8202);
		vo.setReplytext("gggg");
		vo.setReplyer("gggg");
		dao.create(vo);
	};
	
	
	
	@Test
	public void test02ListPage() throws Exception {
		List<ReplyVO> list = dao.list(1);
		for(ReplyVO vo : list) {
			System.out.println(vo);
		}
	}
	
	@Test
	public void testListPage() throws Exception{
		Creiteria cri = new Creiteria();
		List<ReplyVO> list=dao.listPage(8202, cri);
		for(ReplyVO vo : list) {
			System.out.println(vo);
		}
	}
}
