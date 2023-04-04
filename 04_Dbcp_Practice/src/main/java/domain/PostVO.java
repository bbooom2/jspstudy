package domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder //이게 들어있어서 builder가 된것 
@Getter
@Setter
@ToString
public class PostVO { // 값을 가지고 있는 객체 DTO와 거의 동급으로 사용 
	
	private int post_no;
	private String writer; 
	private String title; 
	private String content; 
	private String ip;
	private Date modified_date;
	private Date created_date;

}
