package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.BoardAddService;
import service.BoardDetailService;
import service.BoardListService;
import service.BoardModifyService;
import service.BoardRemoveService;
import service.IBoardService;


@WebServlet("*.do") 
// getAllBoardList.do (실무에서는 gerAllBoards.do를 많이 사용)	getBoardByNo.do  writeBoard.do(작성후이동)  addBoard.do(작성완료)  modifyBoard.do  removeBoard.do 
//(모든 게시글 조회 : getAllBoardList.do) (상세 게시글, 번호에 의한 게시글 가져오기 :   getBoardByNo.do)   
//(게시물 추가 : addBoard.do)  (게시물 수정 : modifyBoard.do)  (게시물 삭제 : removeBoard.do)  (작성화면으로 넘어간다 : writeBoard.do)
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청과 응답의 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// URLMapping 확인  --> 주소창에 딜리트 넘버 번호 해줬을 때 주소창 자체로 삭제될 수 있을 까봐 유알엘매핑처리 (보안) 
		String requestURI = request.getRequestURI();					  /* 	/04_Dbcp/getAllBoards.do  프로젝트 + 파일명	*/ 
		String contextPath = request.getContextPath(); 					  /*	/04_Dbcp				  프로젝트 Path_프로젝트명까지의 경로	*/
		String urlMapping = requestURI.substring(contextPath.length());   /*	/getAllBoardList.do		  리퀘스트 유알아이에서 패스를 잘라낸 나머지 명만 남게 됨 */
		
		// 모든 서비스의 공통 타입 선언 
		IBoardService service = null;
		
		// ActionForward 선언 
		ActionForward af = null;
		
		// URLMapping에 따른 서비스 생성 
		switch(urlMapping) { // 모든 서비스를 선택하는 건 스위치 (어떤 서비스를 쓰겠다 하고 고르는 것) 
		case "/getAllBoardList.do": //목록보기
			service = new BoardListService();
			break;
		case "/getBoardByNo.do":
			service = new BoardDetailService(); //보드에드서비스로 만들어진 서비스의 실행쪽으로 흘러들어가고 있음
			break;
		case "/addBoard.do":
			service = new BoardAddService();
			break;
		case "/modifyBoard.do":
			service = new BoardModifyService();
			break;
		case "/removeBoard.do":
			service = new BoardRemoveService();
			break;
		case "/writeBoard.do":
			af = new ActionForward("board/write.jsp", false); // board 폴더 아래 write.jsp로 forward한다. (단순 이동의 경우 forward한다. 리다이렉트는 하라고 할 때만 진행.)
			break;
		}
		
		
		// 서비스 실행 
		if(service != null) { //execute로 실행하게끔 되어있음 
			af = service.execute(request, response);
			
		}
		
		
		// 응답View로 이동 
		if(af != null) {
			if(af.isRedirect()) {
				response.sendRedirect(af.getPath());
			} else {
				request.getRequestDispatcher(af.getPath()).forward(request, response);
			}
		}
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
