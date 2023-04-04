package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.BoardDTO;
import repository.BoardDAO;

public class BoardDetailService implements IBoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// 1. 요청 파라미터 
		Optional<String> opt = Optional.ofNullable(request.getParameter("board_no"));
		int board_no = Integer.parseInt(opt.orElse("0"));
		
		// 2. BoardDAO의 selectBoardByNo 메소드를 호출 
		BoardDTO board = BoardDAO.getInstance().selectBoardByNo(board_no);
		
		  // 3. 존재하지 않는 게시글의 경우 응답 처리
		
		 // 주소창에 삭제 주소를 입력해서 삭제를 시도하는 경우 잘못된 요청으로 처리한다.
		try { 
			
			
			if(board == null){
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('존재하지 않는 게시글입니다.')");
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
		
		// 4. DB에서 가져온 게시글(BoardDTO board)을 request에 저장(상세보기화면(board/detail.jsp)으로 forward(전달)하기 위해서)
	      request.setAttribute("board", board);
	      
		// 5. 어디로 and 어떻게 이동 
		return new ActionForward("board/detail.jsp", false); //false가 forward이다. 
	}

}
