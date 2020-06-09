package com.hj.s1.board.qna;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.hj.s1.board.BoardRepository;
import com.hj.s1.board.BoardVO;

@Repository //생략가능 
@Mapper
public interface QnaRepository extends BoardRepository{
	public int setRef(BoardVO boardVO) throws Exception;
	public int setReply(BoardVO boardVO) throws Exception;
	public int setReplyUpdate(BoardVO boardVO) throws Exception;
}
