package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.BbsDTO;
import repository.BbsDAO;

public class BbsDetailService implements IBbsService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) { 
		Optional<String> opt = Optional.ofNullable(request.getParameter("bbsNo"));
		int bbsNo = Integer.parseInt(opt.orElse("0")); //혹시 null이었다면 0으로 대체해서 꺼내세요.
		
		BbsDTO bbs = BbsDAO.getInstance().selectBbsByNo(bbsNo);
		
		if(bbs == null) {
			try {
				
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('존재하지 않는 BBS입니다.')");
				out.println("history.back()");
				out.println("</scrtipt>");
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		request.setAttribute("bbs", bbs);
		return new ActionForward("bbs/detail.jsp", false);
	}

}
