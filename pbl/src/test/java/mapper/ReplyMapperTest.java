package mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Board;
import domain.Reply;
import domain.dto.Criteria;
import lombok.extern.slf4j.Slf4j;
import util.MyBatisUtil;

@Slf4j
public class ReplyMapperTest {

	private ReplyMapper replyMapper = MyBatisUtil.getsqlSession().getMapper(ReplyMapper.class);
	
	@Test
	@DisplayName("단일 게시글 조회용 테스트")
	public void testSelectOne() {
		// given
		Long rno = 1L;
		
		// when
		Reply reply = replyMapper.selectOne(rno);
		log.info("{}", reply);
	}
	
	@Test
	@DisplayName("목록 조회")
	public void testlist() {
		List<Reply> list = replyMapper.list(1024L, 50L);
		list.forEach(b -> log.info("{}", b.getContent()));
	}
	@Test
	@DisplayName("목록 조회 null")
	public void testlistRnoNull() {
		List<Reply> list = replyMapper.list(1024L, null);
		list.forEach(b -> log.info("{}", b.getContent()));
	}
	
	@Test
	@DisplayName("댓글 수정 테스트")
	public void testUpdate() {
		Long rno = 10L;
		Reply reply = replyMapper.selectOne(rno);
		reply.setContent("수정");
		replyMapper.update(reply);
	}
	
	@Test
	@DisplayName("댓글 등록 테스트")
	public void testInsert() {
		Reply reply = Reply.builder().content("ㅇㅇ").id("sae0").bno(1024L).build();
		log.info("{}", reply);
		replyMapper.insert(reply);
		log.info("{}", reply);
	}
	
	@Test
	@DisplayName("댓글 삭제 테스트")
	public void testRepDelete() {
		Long rno = 1020L;
		replyMapper.delete(rno);
	}
}
