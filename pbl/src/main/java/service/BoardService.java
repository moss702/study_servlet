package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import domain.Board;
import domain.Member;
import mapper.BoardMapper;
import mapper.MemberMapper;
import util.MyBatisUtil;

public class BoardService {
	public List<Board> list() {
		try(SqlSession session = MyBatisUtil.getsqlSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			return (List<Board>) mapper.list();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Board findBy(long bno) {
		try(SqlSession session = MyBatisUtil.getsqlSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			return mapper.selectOne(bno);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
