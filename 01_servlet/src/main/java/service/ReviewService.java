package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import domain.Review;
import mapper.ReviewMapper;
import util.MyBatisUtil;

public class ReviewService {
	public Review findBy(Long rno) {
		try(SqlSession session = MyBatisUtil.getsqlSession()) {
			ReviewMapper mapper = session.getMapper(ReviewMapper.class);
			return mapper.findBy(rno);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int register(Review review) {
		try(SqlSession session = MyBatisUtil.getsqlSession()) {
			ReviewMapper mapper = session.getMapper(ReviewMapper.class);
			return mapper.insert(review);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int modify(Review review) {
		try(SqlSession session = MyBatisUtil.getsqlSession()) {
			ReviewMapper mapper = session.getMapper(ReviewMapper.class);
			return mapper.update(review);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int remove(Long rno) {
		try(SqlSession session = MyBatisUtil.getsqlSession()) {
			ReviewMapper mapper = session.getMapper(ReviewMapper.class);
			return mapper.delete(rno);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<Review> list() {
		try(SqlSession session = MyBatisUtil.getsqlSession()) {
			ReviewMapper mapper = session.getMapper(ReviewMapper.class);
			return mapper.select();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
//	public static void main(String[] args) {
//		ReviewService service = new ReviewService();
//
////		service.register(Review.builder().content("서비스 테스트").pno(1L).rating(5).writer("새똥이").build());
//		//데이터 등록
//				
////		Review review = service.findBy(1L);
////		review.setContent("변경된 내용");
////		service.modify(review);
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
