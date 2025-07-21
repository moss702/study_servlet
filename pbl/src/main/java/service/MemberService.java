package service;

import java.util.Base64.Decoder;

import org.apache.ibatis.session.SqlSession;

import domain.Member;
import lombok.extern.slf4j.Slf4j;
import mapper.MemberMapper;
import util.MyBatisUtil;
import util.PasswordEncoder;

@Slf4j
public class MemberService {

	public int register(Member member) {
		try(SqlSession session = MyBatisUtil.getsqlSession()) {
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			member.setPw(PasswordEncoder.encode(member.getPw()));
		
			return mapper.insert(member);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public Member findById(String id) {
		try(SqlSession session = MyBatisUtil.getsqlSession()) {
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			return mapper.findById(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean login(String id, String pw) {
		Member member = findById(id);
		if(member == null) { //없는 회원일때의 null값 처리
			return false;
		}
		return PasswordEncoder.matches(pw, member.getPw());
		// (평문*입력받은값, 암호화 된거*보관하고있는거)
	}

	
	public static void main(String[] args) {
		MemberService memberService = new MemberService();
//		Member member = Member.builder().id("bae").pw("1234").build();
//		memberService.register(member);
//		log.info("{}", memberService.findById("sae"));
//		log.info("{} dd {}", 10,20);
//		memberService.register(Member.builder().id("sae0").pw("1234").name("새똥이").build());
		log.info("{}", memberService.login("sae0", "1234"));
		log.error("{}", memberService.login("sae0", "12340"));
		
	}
}
