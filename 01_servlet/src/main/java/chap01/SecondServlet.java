package chap01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = {"/SecondServlet", "/Servlet2"})
public class SecondServlet extends HttpServlet {

	// text/html, text/plain, text/xml, application/json
	// image/png,
	// 이런것들을 mime-type이라고 함.
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 기본응답형태 text/plain
		
		resp.setContentType("text/html; charset=euc-kr");
		PrintWriter out = resp.getWriter();
		out.write("print writer의 부모 writer. 이렇게도 쓸수있음");
		System.out.println("서버 콘솔에 출력 abc");
		out.println("<h1>화면에 출력 123 ab</h1>");
	}

}
