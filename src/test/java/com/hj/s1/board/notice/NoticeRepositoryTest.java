package com.hj.s1.board.notice;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoticeRepositoryTest {
	@Autowired
	private NoticeRepository noticeRepository;
	
	@Test
	void setInsertTest() throws Exception {
		for(int i=0; i<100; i++) {
			NoticeVO noticeVO = new NoticeVO();
			noticeVO.setTitle("title" + i);
			noticeVO.setWriter("writer" + i);
			noticeVO.setContents("contents" + i);
			int res = noticeRepository.setInsert(noticeVO);
		}
		//assertEquals(1, res);
	}
	
//	@Test
//	void setUpdateTest() throws Exception {
//		NoticeVO noticeVO = new NoticeVO();
//		noticeVO.setNum(1);
//		noticeVO.setTitle("title_update");
//		noticeVO.setContents("contents_update");
//		int res = noticeRepository.setUpdate(noticeVO);
//		assertEquals(1, res);
//	}
//	
//	@Test
//	void setDeleteTest() throws Exception {
//		NoticeVO noticeVO = new NoticeVO();
//		noticeVO.setNum(2);
//		int res = noticeRepository.setDelete(noticeVO);
//		assertEquals(1, res);
//	}
}
