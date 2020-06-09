package com.hj.s1.board.qna;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QnaRepositoryTest {
	@Autowired
	private QnaRepository qnaRepository;
	
	@Test
	void setInsertTest() throws Exception {
		for(int i=0; i<100; i++) {
			QnaVO qnaVO = new QnaVO();
			qnaVO.setTitle("title" + i);
			qnaVO.setWriter("writer" + i);
			qnaVO.setContents("contents" + i);
			int res = qnaRepository.setInsert(qnaVO);
		}
		//assertEquals(1, res);
	}

}
