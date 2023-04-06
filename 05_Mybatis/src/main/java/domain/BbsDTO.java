package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 				// 게터세터투스트링역할을 하는 것 
@NoArgsConstructor  // 매개변수 없는 생성자 생성 
@AllArgsConstructor // 매개변수 받는 생성자 생성 
public class BbsDTO {
	
	private int bbsNo;
	private String title; 
	private String content; 
	private String modifiedDate; 
	private String createdDate; 

}
