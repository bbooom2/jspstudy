package ex06_forward;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ForwardServlet1")

public class ForwardServlet1 extends HttpServlet {
   private static final long serialVersionUID = 1L;
       

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      // 포워드 이전(첫 번째 요청) 파라미터 확인
      String model = request.getParameter("model");
      System.out.println("ForwardServlet1 : " + model);
      
      // 포워드(전달) -> 값을 넘겨주는 것, 정확하게는 request(저장소)나 response(저장소x) 를 넘겨줌 
      request.getRequestDispatcher("/ForwardServlet2").forward(request, response);
      
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      doGet(request, response);
   }

}