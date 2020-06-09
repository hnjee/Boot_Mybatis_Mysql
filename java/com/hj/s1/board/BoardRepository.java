package com.hj.s1.board;

import java.util.List;

import com.hj.s1.util.Pager;

//DAO = Repository
public interface BoardRepository {
	//Set 
	//insert
	public int setInsert(BoardVO boardVO) throws Exception;
	//update
	public int setUpdate(BoardVO boardVO) throws Exception;
	//delete
	public int setDelete(BoardVO boardVO) throws Exception;
	
	//Get
	//selectOne
	public BoardVO getSelectOne(BoardVO boardVO) throws Exception;
	//selectList
	public List<BoardVO> getSelectList(Pager pager) throws Exception;
	//totalCount (paging)
	public long getTotalCount(Pager pager) throws Exception;
	//Update RefNum
	
}
