package domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor //디폴트 생성자 
@AllArgsConstructor // generate using field
@Getter
@Setter
public class BoardDTO {
	
	private int board_no;
	private String title; // b조
	private String content; // 화이팅 
	private Date modified_date;
	private Date created_date;

}
