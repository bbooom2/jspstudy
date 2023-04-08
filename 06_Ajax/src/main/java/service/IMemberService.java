package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IMemberService {
	
	// 평소에 쓰는 인터페이스와 다른게 액션포워드가 없어서 그럼. 
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception; 
	
}
