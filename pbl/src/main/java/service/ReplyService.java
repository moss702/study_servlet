package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import domain.Member;
import domain.Reply;
import mapper.ReplyMapper;
import util.MyBatisUtil;

public class ReplyService {
	public Reply findBy(Long rno) {
		try(SqlSession session = MyBatisUtil.getsqlSession()) {
			ReplyMapper mapper = session.getMapper(ReplyMapper.class);
			return mapper.selectOne(rno);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int register(Reply Reply) {
		try(SqlSession session = MyBatisUtil.getsqlSession()) {
			ReplyMapper mapper = session.getMapper(ReplyMapper.class);
			mapper.insert(Reply);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int modify(Reply Reply) {
		try(SqlSession session = MyBatisUtil.getsqlSession()) {
			ReplyMapper mapper = session.getMapper(ReplyMapper.class);
			mapper.update(Reply);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int remove(Long rno) {
		try(SqlSession session = MyBatisUtil.getsqlSession()) {
			ReplyMapper mapper = session.getMapper(ReplyMapper.class);
			mapper.delete(rno);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<Reply> list(Long bno, Long lastRno) {
		try(SqlSession session = MyBatisUtil.getsqlSession()) {
			ReplyMapper mapper = session.getMapper(ReplyMapper.class);
			return mapper.list(bno, lastRno);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
//	public static void main(String[] args) {
//		ReplyService service = new ReplyService();
//
////		service.register(Reply.builder().content("서비스 테스트").pno(1L).rating(5).writer("새똥이").build());
//		//데이터 등록
//				
////		Reply Reply = service.findBy(1L);
////		Reply.setContent("변경된 내용");
////		service.modify(Reply);
////		System.out.println(service.findBy(1L));
//		//rno 찾아서 수정(update)
//		
////		service.remove(5L);
//		//rno 찾아서 삭제
//		
//		System.out.println("================");
//		service.list().forEach(System.out::println);
//		
//		
//	}
	
}
