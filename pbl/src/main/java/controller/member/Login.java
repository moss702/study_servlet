package controller.member;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Member;
import domain.dto.Criteria;
import lombok.extern.slf4j.Slf4j;
import service.MemberService;
import util.HikariCPUtil;
import util.ParamUtil;

@WebServlet("/member/login")
@Slf4j
public class Login extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Member member = ParamUtil.get(req, Member.class);
		boolean ret = new MemberService().login(member.getId(), member.getPw());
		log.info("{}", ret);
		
//		String id = req.getParameter("id");
//		String pw = req.getParameter("pw"); //중간값 제대로 수집했나 확인 수시로!
//		new MemberService().login(id,pw);
//		boolean ret = new MemberService().login(id,pw);
		
		if(ret) { //로그인 성공
			HttpSession session = req.getSession();
			session.setMaxInactiveInterval(60 * 10); //세션 유지기간 *10분
			session.setAttribute("member", new MemberService().findById(member.getId()));
			
			//로그인 성공했을때 보낼곳
			String url = req.getParameter("url");
			if(url == null) {
				resp.sendRedirect(req.getContextPath() + "/index"); //contextPath >> /pbl
			} else {
				//쿼리스트링에 한글 포함될수도있으니까 차셋 및 디코딩
				String decodedUrl = URLDecoder.decode(url, "utf-8");
				Criteria cri = Criteria.init(req);
				resp.sendRedirect(decodedUrl + "?" + cri.getQs2());
			}
			
		} else {
			resp.sendRedirect("login?msg=fail");
		}
		
	}
}
