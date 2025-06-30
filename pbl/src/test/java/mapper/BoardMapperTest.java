package mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Board;
import domain.Reply;
import domain.dto.Criteria;
import lombok.extern.slf4j.Slf4j;
import util.MyBatisUtil;

@Slf4j
public class BoardMapperTest {

	private BoardMapper boardMapper = MyBatisUtil.getsqlSession().getMapper(BoardMapper.class);

	
	@Test
	public void addTest() {
		int result = 1 + 1;
		assertEquals(10, result);
		//2라는 값과 result가 같은 값인가?
	}
	
	@Test
	@DisplayName("단일 게시글 조회용 테스트")
	public void testSelectOne() {
		// given
		Long bno = 1028L;
		
		// when
		Board board = boardMapper.selectOne(bno);
		
		// then ~ actual, expect
		assertNotNull(board);
		
		log.info("{}", board);
	}
	
	@Test
	@DisplayName("목록 조회 3페이지 10개씩 2번 카테고리")
	public void testlist() {
		Criteria cri = new Criteria(3, 10, 2);
		List<Board> list = boardMapper.list(cri);
		list.forEach(b -> log.info("{}", b.getTitle()));
	}
	
	@Test
	@DisplayName("목록 조회 3페이지 10개씩 2번 카테고리")
	public void testlist1() {
		Criteria cri = new Criteria(3, 10, 2);
		List<Board> list = boardMapper.list(cri);
		list.forEach(b -> log.info("{} {} {}", b.getAttachCnt(), b.getReplyCnt(), b.getAttachs()));
	}
	
	@Test
	@DisplayName("목록 조회 검색테스트")
	public void testlist2() {
		Criteria cri = new Criteria(1, 10, 2, "TI", "점메추");
		log.info(Arrays.toString(cri.getTypes()));
		List<Board> list = boardMapper.list(cri);
//		list.forEach(b -> log.info("{}", b.getTitle()));
	}
	
	@Test
	@DisplayName("글수정테스트")
	public void testUpdate() {
		Long bno = 1023L;
		Board board = boardMapper.selectOne(bno);
		board.setTitle("제목 수정");
		
		boardMapper.update(board);
	}
	
	
	@Test
	@DisplayName("조회 증가 테스트")
	public void testIncreaseCnt() {
		Long bno = 1028L;
		boardMapper.increaseCnt(bno);
	}
	
	@Test
	@DisplayName("maxSeq 조회")
	public void testSelectMaxSeq(){
		Board parent = boardMapper.selectOne(1076L);
		int maxSeq = boardMapper.selectMaxSeq(parent);
		log.info("", maxSeq);
	}
	
}


