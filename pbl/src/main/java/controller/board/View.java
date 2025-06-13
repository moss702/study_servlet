package controller.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Board;
import service.BoardService;

@WebServlet("/board/view")
public class View extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bno = req.getParameter("bno");
		if(bno == null) {
			resp.setContentType("text/html; charset=utf-8");
			PrintWriter pw = resp.getWriter();
			pw.print("<script>");
			pw.print("alert('잘못된 접근입니다.');");
			pw.print("location.href = 'list' ");
			pw.print("</script>");
			return;
		}
		
		BoardService service = new BoardService();
		Board board = service.findBy(Long.parseLong(bno));
		req.setAttribute("board", board);
		req.getRequestDispatcher("/WEB-INF/views/board/view.jsp").forward(req, resp);
		
	}	
}
