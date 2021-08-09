package kim.youngeun.board.model;

import lombok.Data;

@Data
public class BoardForm {
	
	private int id;
	
	private String writer;
	
	private String title;
	
	private String content;

}
