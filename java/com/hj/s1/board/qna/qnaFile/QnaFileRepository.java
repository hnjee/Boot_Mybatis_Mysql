package com.hj.s1.board.qna.qnaFile;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QnaFileRepository {
	public int setInsert(QnaFileVO qnaFileVO) throws Exception;
	public QnaFileVO fileDown(QnaFileVO qnaFileVO) throws Exception;
}
