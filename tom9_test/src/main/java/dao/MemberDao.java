package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Member;
import utill.DBUtil;

//DAO
//Data Access Objects

public class MemberDao {
	//insert
	//select
	
	public static void main(String[] args) {
		new MemberDao().insert(Member.builder().id("sample").pw("1234").name("개똥이").build());
		System.out.println(new MemberDao().selectOne(1L));
		System.out.println(new MemberDao().selectOne(3L));
	}

	
	public void insert(Member member) {
		// 1. 접속객체 취득
		// 2. 문장생성
		// 3. 실행 후 처리
		Connection conn = DBUtil.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO member(ID,PW,name) VALUES (?,?,?)");
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getName());
			
			pstmt.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Member selectOne(Long num) {
		Connection conn = DBUtil.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from member where num = ?");
			pstmt.setLong(1, num);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Member member = Member.builder()
						.num(rs.getLong("num"))
						.id(rs.getString("id"))
						.pw(rs.getString("pw"))
						.name(rs.getString("name"))
						.regDate(rs.getDate("regdate"))
						.build();
				
				return member;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Member selectOne(String id) {
		Connection conn = DBUtil.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from member where id = ?");
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Member member = Member.builder()
						.num(rs.getLong("num"))
						.id(rs.getString("id"))
						.pw(rs.getString("pw"))
						.name(rs.getString("name"))
						.regDate(rs.getDate("regdate"))
						.build();
				
				return member;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
