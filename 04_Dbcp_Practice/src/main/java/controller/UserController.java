package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("*.user") //매핑이 유저로 끝난다. 우리끼리의 약속  /login.user
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청, 응답 인코딩 
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// urlMapping 
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length()); // 컨텍스트패스 길이가 url매핑이다 
		
		
		// urlMapping에 따른 서비스 선택(생성) 
		switch(urlMapping) {
		case "/login.user":
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			if(id.equals(pw)) {  // id와 pw가 같으면 로그인 성공으로 보자.
				HttpSession session = request.getSession();
				session.setAttribute("loginId", id);
				response.sendRedirect(request.getContextPath() + "/list.post");
			}
		}
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
