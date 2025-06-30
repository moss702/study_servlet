package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import domain.Board;
import domain.dto.Criteria;
import lombok.extern.slf4j.Slf4j;
import mapper.AttachMapper;
import mapper.BoardMapper;
import mapper.ReplyMapper;
import util.MyBatisUtil;

@Slf4j
public class BoardService {
    public List<Board> list(Criteria cri) { 
        try (SqlSession session = MyBatisUtil.getsqlSession()) {
            BoardMapper mapper = session.getMapper(BoardMapper.class);
            List<Board> list = mapper.list(cri);
            long cnt = mapper.getCount(cri);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Board findBy(Long bno) {
        try (SqlSession session = MyBatisUtil.getsqlSession()) {
            BoardMapper mapper = session.getMapper(BoardMapper.class);
            mapper.increaseCnt(bno);
            Board board = mapper.selectOne(bno);
            return board;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    

    public void write(Board board) {
    	SqlSession session = MyBatisUtil.getsqlSession(false);
    	try {
            BoardMapper mapper = session.getMapper(BoardMapper.class);
            Long bno = board.getBno();
            if (bno == null) {   // 답글이 아님 :: 신규 글
            	mapper.insert(board);
            	mapper.updateGrpMyself(board);
            } else {  // 답글임
        		// 1. 부모글 조회 * 부모의 파라미터 가져와서 조정하기
        		Board parent = mapper.selectOne(bno);
        		
        		// 2. maxSeq 취득 * select
        		int maxSeq = mapper.selectMaxSeq(parent);
        		board.setSeq(maxSeq + 1); //순서 지정 * 부모보다 +1
        		
        		// 3. 해당 조건의 게시글들의 seq 밀기 = 내 위치에 작성하기 위한 update
        		board.setGrp(parent.getGrp());
        		board.setDepth(parent.getDepth()+1); //깊이 지정 * 부모보다 +1
        		mapper.updateSeqIncrease(board);
        		
        		// insert * 답글 등록하기
        		mapper.insertChild(board);
            }
            
            AttachMapper attachMapper = session.getMapper(AttachMapper.class);
            board.getAttachs().forEach(a -> {
            	a.setBno(board.getBno());
            	attachMapper.insert(a);
            });
            session.commit();
        } catch (Exception e) { //중간에 문제 있으면 롤백
        	session.rollback(); 
            e.printStackTrace();
        } finally { //성공 또는 실패 상관없이 완료되면 세션 close
        	session.close();
        }
    }
    
	public void modify(Board board) {
	   	SqlSession session = MyBatisUtil.getsqlSession(false);
		try {
	        BoardMapper mapper = session.getMapper(BoardMapper.class);
	        mapper.update(board);
	        
	        AttachMapper attachMapper = session.getMapper(AttachMapper.class);
	        // 기존 첨부파일의 메타데이터 제거
	        attachMapper.deleteByBno(board.getBno());
	        
	        // 새로 첨부파일 메타데이터 등록
	        board.getAttachs().forEach(a -> {
	        	a.setBno(board.getBno());
	        	attachMapper.insert(a);
	        });
	        session.commit();
	    } catch (Exception e) { //중간에 문제 있으면 롤백
	    	session.rollback(); 
	        e.printStackTrace();
	    } finally { //성공 또는 실패 상관없이 완료되면 세션 close
	    	session.close();
	    }
    }

    public void remove(Long bno) {
	   	SqlSession session = MyBatisUtil.getsqlSession(false);
		try {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
            AttachMapper attachMapper = session.getMapper(AttachMapper.class);
            ReplyMapper replyMapper = session.getMapper(ReplyMapper.class);

            replyMapper.deleteByBno(bno);
            attachMapper.deleteByBno(bno);
            mapper.delete(bno);
            
            session.commit();
	    } catch (Exception e) { //중간에 문제 있으면 롤백
	    	session.rollback();
	        e.printStackTrace();
	    } finally { //성공 또는 실패 상관없이 완료되면 세션 close
	    	session.close();
	    }
    }

    
    public long getCount(Criteria cri) {
        try (SqlSession session = MyBatisUtil.getsqlSession()) {
            BoardMapper mapper = session.getMapper(BoardMapper.class);
            return mapper.getCount(cri);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}