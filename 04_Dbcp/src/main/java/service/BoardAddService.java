package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.BoardDTO;
import repository.BoardDAO;

public class BoardAddService implements IBoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) { // write.jsp에서 출발한 게 BoardController를 거쳐서 BoardAddService로 전달, request 중요 
		
		// 1. 요청 파라미터 
		String title = request.getParameter("title"); // 타이틀을 나에게 달라 
		String content = request.getParameter("content"); // 컨텐트 파라미터를 나에게 달라 
		
		
		// 2. BoardDTO 객체 생성 _ 데이타를 전송할때 사용하는 객체 transfer object DTO _이때 객체대신 사용할 수 있는 게 Map
		BoardDTO board = new BoardDTO(); //이 가방에 전해놓기 위해 디티오 객체를 생성시켜놓음. 이 보드객체가 보드디티오가 되고 필드 5개만들어놓은게 됨 -> 보드 
		board.setTitle(title); // b조 
		board.setContent(content); // 화이팅 
		
		
		// 3. 삽입을 위해 DB로 BoardDTO를 전달(BoardDAO(<- 접근객체 )의 insertBoard 메소드) 
		int insertResult = BoardDAO.getInstance().insertBoard(board); // BoardDAO.getInstance() 까지를 DAO라고 함_0이 넘어오면 삽입된 게 없는 거니까 실패, 1이 나오면 성공 
		
		System.out.println(insertResult == 1 ? "삽입 성공" : "삽입 실패"); //지금 서비스가 틀려있어서 그거 찾으려고 해보는 것 3번까지는 성공이라 삽입 성공 나온 것 4번이 문제라 수정완료. request.getContextPath() 추가
		
		// 4. 어디로 and 어떻게 이동 
		return new ActionForward(request.getContextPath() + "/getAllBoardList.do", true); 
		// true 라는 것은 redirect 를 이용하겠다는 것. 왜 redirect를 하는가? insert, update, delete 이후에는 리다이렉트를 한다. 포워드는 셀렉문할때 사용한다. 
		//액션포워드가 화면전환을 위한 거여서 게시글 작성후 보드리스트로 가겠다는 것. 
		// 응답을 끊고 가겠다는 게 리다이렉트 응답을 끊지 않고 가겠다는 게 포워드 (게시글 목록에서 게시글 눌렀을때 응답을 유지한채로 게시글 상세보기로 넘어가므로 이것은 포워드) 
		// 업체 전화걸었을때 번호 받아서 다시 걸게되면 리다이렉트 / 내선번호 연결해주면 포워드 
	}

}
