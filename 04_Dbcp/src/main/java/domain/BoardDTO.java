package domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor   //default 생성자 
@AllArgsConstructor // generate using field
@Getter
@Setter
public class BoardDTO {
	
	// 컬럼명을 필드로 선언하여 식별자로 사용(DB 컬럼명고 일치하지 않아도 된다.)
	private int board_no;
	private String title;   // b조
	private String content; // 화이팅 
	private Date modified_date;
	private Date created_date;

}
