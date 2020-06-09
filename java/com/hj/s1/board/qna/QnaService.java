package com.hj.s1.board.qna;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hj.s1.board.BoardService;
import com.hj.s1.board.BoardVO;
import com.hj.s1.board.notice.noticeFile.NoticeFileRepository;
import com.hj.s1.board.notice.noticeFile.NoticeFileVO;
import com.hj.s1.board.qna.qnaFile.QnaFileRepository;
import com.hj.s1.board.qna.qnaFile.QnaFileVO;
import com.hj.s1.util.FileManager;
import com.hj.s1.util.FilePathGenerator;
import com.hj.s1.util.Pager;

@Service
@Transactional(rollbackFor = Exception.class)
public class QnaService implements BoardService{
	@Autowired
	private QnaRepository qnaRepository;
	
	@Autowired
	private QnaFileRepository qnaFileRepository;
	@Autowired
	private FilePathGenerator pathGenerator;
	@Autowired
	private FileManager fileManager;
	@Value("${board.qna.filePath}")
	private String filePath;

	
	
	@Override
	public int setInsert(BoardVO boardVO, MultipartFile[] files) throws Exception {
		int res = qnaRepository.setInsert(boardVO);
		res = qnaRepository.setRef(boardVO); //ref에 num넣기 
		
		//파일처리
		File file = pathGenerator.getUseClassPathResource(filePath);
		for(MultipartFile f: files) {
			if(f.getSize()<=0) {
				continue;
			}
			String fileName = fileManager.saveFileCopy(f,file);
			
			QnaFileVO vo = new QnaFileVO();
			vo.setNum(boardVO.getNum());
			vo.setFileName(fileName);
			vo.setOriName(f.getOriginalFilename());
			qnaFileRepository.setInsert(vo);
		}
		return res;
	}

	@Override
	public int setUpdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return qnaRepository.setUpdate(boardVO);
	}

	@Override
	public int setDelete(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return qnaRepository.setDelete(boardVO);
	}

	@Override
	public List<BoardVO> getSelectList(Pager pager) throws Exception {
		pager.makeRow();
		long totalCount = this.getTotalCount(pager);
		pager.makePage(totalCount);
		//System.out.println("전체 글의 수: "+ totalCount);
		
		return qnaRepository.getSelectList(pager);
	}

	@Override
	public BoardVO getSelectOne(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return qnaRepository.getSelectOne(boardVO);
	}

	@Override
	public long getTotalCount(Pager pager) throws Exception {
		// TODO Auto-generated method stub
		return qnaRepository.getTotalCount(pager);
	}
	
	public QnaFileVO fileDown(QnaFileVO qnaFileVO)throws Exception{
		return qnaFileRepository.fileDown(qnaFileVO);
	}
	
	public int setReply(BoardVO boardVO, MultipartFile[] files) throws Exception{
		int res = qnaRepository.setReply(boardVO);
		res = qnaRepository.setReplyUpdate(boardVO);
		//파일처리
		File file = pathGenerator.getUseClassPathResource(filePath);
		for(MultipartFile f: files) {
			if(f.getSize()<=0) {
				continue;
			}
			String fileName = fileManager.saveFileCopy(f,file);
			
			QnaFileVO vo = new QnaFileVO();
			vo.setNum(boardVO.getNum());
			vo.setFileName(fileName);
			vo.setOriName(f.getOriginalFilename());
			qnaFileRepository.setInsert(vo);
		}
		return res;
	}
}
