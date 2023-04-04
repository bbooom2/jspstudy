package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.BoardDTO;
import repository.BoardDAO;

public class BoardAddService implements IBoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) { // write.jsp에서 출발한 게 컨트롤러를 거쳐서 보드에드서비스로 전달 request 중요 
		
		// 1. 요청 파라미터 
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		
		// 2. BoardDTO 객체 생성 _ 데이타를 전송할때 사용하는 객체 transfer object DTO _이때 객체대신 사용할 수 있는 게 Map
		BoardDTO board = new BoardDTO();
		board.setTitle(title);
		board.setContent(content);
		
		
		// 3. 삽입을 위해 DB로 BoardDTO를 전달(BoardDAO(<- 접근객체 )의 insertBoard 메소드) 
		int insertResult = BoardDAO.getInstance().insertBoard(board); // BoardDAO.getInstance() 까지를 DAO라고 함_0이 넘어오면 삽입된 게 없는 거니까 실패, 1이 나오면 성공 
		
		System.out.println(insertResult == 1 ? "삽입 성공" : "삽입 실패"); //지금 서비스가 틀려있어서 그거 찾으려고 해보는 것 3번까지는 성공이라 삽입 성공 나온 것 4번이 문제라 수정완료. request.getContextPath() 추가
		
		// 4. 어디로 and 어떻게 이동 
		return new ActionForward(request.getContextPath() + "/getAllBoardList.do", true); 
		// true 라는 것은 redirect 를 이용하겠다는 것. 왜 redirect를 하는가? insert, update, delete 이후에는 리다이렉트를 한다. 
	}

}
