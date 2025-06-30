package controller.reply;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import domain.Member;
import domain.Reply;
import lombok.extern.slf4j.Slf4j;
import service.ReplyService;
import util.JsonRespUtil;

@WebServlet("/reply/*")
@Slf4j
public class ReplyServlet extends HttpServlet{
	private static final String ID = "/reply/";
	
	private String getURI(HttpServletRequest req) {
		String uri = req.getRequestURI();
		uri = uri.substring(uri.indexOf(ID) + ID.length());
		return uri;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = getURI(req);
		ReplyService service = new ReplyService();
		Gson gson = new Gson();
//		String ret = "";
		Object o = null;
		if(uri.startsWith("list") || uri.equals("*")) { // review/list/1 목록조회
			log.info("{}", uri);
			String tmp = "list/";
			if(uri.contains(tmp)) {
				String[] tmps = uri.split("/");
				Long bno = null;
				
				if(tmps.length > 1) {
					Long lastRno = null;
					bno = Long.valueOf(tmps[1]);
					if (tmps.length > 2) {
						lastRno = Long.valueOf(tmps[2]);						
					}
					o = service.list(bno, lastRno);
				}
			}
		} 
		else { // review/1 단일조회
			o = service.findBy(Long.parseLong(uri)); //uri의 숫자를 long타입으로 바꿔준다.
		} 
		JsonRespUtil.writeJson(resp, o);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = getURI(req);
		Long rno = Long.valueOf(uri);
		
		new ReplyService().remove(rno);
		
//		resp.setContentType("application/json; charset=utf-8");
//		resp.getWriter().print(new Gson().toJson(Map.of("result", true)));
		JsonRespUtil.writeJson(resp, Map.of("result", true));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//insert
//		String ret = String.join("", req.getReader().lines().toList());		
//		Reply reply = new Gson().fromJson(ret, Reply.class);
		Reply reply = JsonRespUtil.readJson(req, Reply.class);
		
		// 이 시점 rno == null 
		new ReplyService().register(reply);
		// 이 시점 rno == not null
		
//		resp.setContentType("application/json; charset=utf-8");
//		resp.getWriter().print(new Gson().toJson(Map.of("result", true, "reply", reply)));
		JsonRespUtil.writeJson(resp, Map.of("result", true, "reply", reply));
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
//		String ret = String.join("", req.getReader().lines().toList());		
//		Reply reply = new Gson().fromJson(ret, Reply.class);
		Reply reply = JsonRespUtil.readJson(req, Reply.class);
		
		new ReplyService().modify(reply);
		
//		resp.setContentType("application/json; charset=utf-8");
//		resp.getWriter().print(new Gson().toJson(Map.of("result", true)));
		JsonRespUtil.writeJson(resp, Map.of("result", true));
		
	}
}
