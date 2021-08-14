package kim.youngeun.board.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class BoardForm {
	
	private Long id;
	private String writer;
	private String title;
	private String content;
	private LocalDateTime created_at;
	private LocalDateTime modified_at;
	
	@Builder
	public BoardForm(Long id, String writer, String title, String content, LocalDateTime created_at, LocalDateTime modified_at) {
		this.id= id;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.created_at = created_at;
		this.modified_at = modified_at;
	}

}
