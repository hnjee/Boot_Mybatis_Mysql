package com.hj.s1.board.notice;

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
import com.hj.s1.util.FileManager;
import com.hj.s1.util.FilePathGenerator;
import com.hj.s1.util.Pager;

@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeService implements BoardService{

	@Autowired
	private NoticeRepository noticeRepository;
	
	@Autowired
	private NoticeFileRepository noticeFileRepository;
	@Autowired
	private FilePathGenerator pathGenerator;
	@Autowired
	private FileManager fileManager;
	@Value("${board.notice.filePath}")
	private String filePath;
	
	@Override
	public int setInsert(BoardVO boardVO, MultipartFile [] files) throws Exception {
		// TODO Auto-generated method stub
		File file = pathGenerator.getUseClassPathResource(filePath);
		noticeRepository.setInsert(boardVO);
		
		for(MultipartFile multipartFile: files) {
			if(multipartFile.getSize()<=0) {
				continue;
			}
			String fileName= fileManager.saveFileCopy(multipartFile, file);
			
			NoticeFileVO vo = new NoticeFileVO();
			vo.setNum(boardVO.getNum());
			vo.setFileName(fileName);
			vo.setOriName(multipartFile.getOriginalFilename());
			noticeFileRepository.setInsert(vo);
			
			//System.out.println(fileName); 
		}
		return 0; //
	}

	@Override
	public int setUpdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeRepository.setUpdate(boardVO);
	}

	@Override
	public int setDelete(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeRepository.setDelete(boardVO);
	}

	@Override
	public List<BoardVO> getSelectList(Pager pager) throws Exception {
		pager.makeRow();
		long totalCount = this.getTotalCount(pager);
		pager.makePage(totalCount);
		//System.out.println("전체 글의 수: "+ totalCount);
		
		return noticeRepository.getSelectList(pager);
	}
	
	@Override
	public BoardVO getSelectOne(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeRepository.getSelectOne(boardVO);
	}

	@Override
	public long getTotalCount(Pager pager) throws Exception {
		return noticeRepository.getTotalCount(pager);
	}
	
	public NoticeFileVO fileDown(NoticeFileVO noticeFileVO)throws Exception{
		return noticeFileRepository.fileDown(noticeFileVO);
	}

}
