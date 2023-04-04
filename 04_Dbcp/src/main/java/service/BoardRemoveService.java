package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.BoardDAO;

public class BoardRemoveService implements IBoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
	      // 주소창에 삭제 주소를 입력해서 삭제를 시도하는 경우 잘못된 요청으로 처리한다.
		try { 
			
			
		if(request.getMethod().equalsIgnoreCase("get")){
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 요청입니다.')");
			out.println("history.back()"); // 뒤로가기 1번 
			out.println("</script>");
			out.flush();
			out.close();
			return null; // null 반환시 액션포워드가 널값으로 넘어갔다는 뜻. 잘못됐을 때 excute가  null 반영  	 그러면 서비스 실행결과로 af가 null값으로 넘어간것. 그러 그다음 이프가 실행이 안됨 . 아무것도 반환하지 말라고 null값을 주는 것. 내가 응답을 통해 직접 이동을 시켰기 때문(자바스크립트코드) 컨트롤러로 널값을 반환시켜서 액션포워드가 없게 반환해주느 ㄴ것. 액션포워드가 없으면 컨트롤러로 반환은 없음. 
			//컨트롤러로 널값을 반환하면 컨트롤러는 이동(redirect 또는 forward)을 하지 않는다. 
			//서비스에서 직접 이동하는 경우에 이 방법을 사용한다. 
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	
		// 1. 요청 파라미터 
		Optional<String> opt = Optional.ofNullable(request.getParameter("board_no"));
		int board_no = Integer.parseInt(opt.orElse("0")); // 공백 처리 : 폼 안에 인풋으로 내장된 경우에 널값으로 전달되지 않음 왜냐면 네임이 있으니까. 받는 측에서는 무조건 있는 것. 값이 입력되어있지 않응ㄹ 수 있어서 그거를 빈문자열로 인식할 수 있게 하는 것.  
		
		// 2. BoardDAO의 deleteBoard 메소드 호출 
		int deleteResult = BoardDAO.getInstance().deleteBoard(board_no);
		System.out.println(deleteResult == 1? "삭제성공" : "삭제실패");
		
		// 3. 어디로 and 어떻게 이동
		
		
		return new ActionForward(request.getContextPath() + "/getAllBoardList.do", true); // 목록으로 리다이렉트하기 
	}

}
