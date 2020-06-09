package com.hj.s1.board;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hj.s1.util.Pager;

public interface BoardService {
	//Set 
	//insert
	public int setInsert(BoardVO boardVO, MultipartFile[] files) throws Exception;
	//update
	public int setUpdate(BoardVO boardVO) throws Exception;
	//delete
	public int setDelete(BoardVO boardVO) throws Exception;
	
	//Get
	//selectList
	public List<BoardVO> getSelectList(Pager pager) throws Exception;
	//selectOne
	public BoardVO getSelectOne(BoardVO boardVO) throws Exception;
	//totalCount
	public long getTotalCount(Pager pager) throws Exception;
	//조회수 업데이트 
}
