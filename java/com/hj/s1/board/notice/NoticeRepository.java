package com.hj.s1.board.notice;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.hj.s1.board.BoardRepository;

@Repository //생략가능 
@Mapper
public interface NoticeRepository extends BoardRepository {

}
