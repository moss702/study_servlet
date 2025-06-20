package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import domain.Board;
import domain.dto.Criteria;
import lombok.extern.slf4j.Slf4j;
import mapper.BoardMapper;
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
            return mapper.selectOne(bno);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    

    public void write(Board board) {
        // 나중에 글번호 반환 필요할수도 있음
        try (SqlSession session = MyBatisUtil.getsqlSession()) {
            BoardMapper mapper = session.getMapper(BoardMapper.class);
            mapper.insert(board);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
	public void modify(Board board) {
        try (SqlSession session = MyBatisUtil.getsqlSession()) {
            BoardMapper mapper = session.getMapper(BoardMapper.class);
            mapper.update(board);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
    
    public void remove(Long bno) {
        try (SqlSession session = MyBatisUtil.getsqlSession()) {
            BoardMapper mapper = session.getMapper(BoardMapper.class);
            mapper.delete(bno);
        } catch (Exception e) {
            e.printStackTrace();
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