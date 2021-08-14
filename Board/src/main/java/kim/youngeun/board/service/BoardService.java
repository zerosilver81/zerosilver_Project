package kim.youngeun.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kim.youngeun.board.model.Board;
import kim.youngeun.board.model.BoardForm;
import kim.youngeun.board.repository.BoardRepository;

@Service
public class BoardService {
	
	@Autowired
	BoardRepository boardRepo;

	public List<Board> searchAll() {
		
		return boardRepo.findAll(); 
	}
	
	public void writePost(Board board) {
		boardRepo.save(board);
	}
	
	public Board findByno(Long id) {
		
		return boardRepo.findById(id).orElse(null);
	}
	
	public Board getBoardId(Long id) {
		
		return boardRepo.getById(id);
	}

	
	public void updateBoard(Board board) {

		boardRepo.save(board);
	}
	
	public void deleteBoard(Long id) {
		
		boardRepo.deleteById(id);
	}
	
	@Transactional
	public List<BoardForm> searchPost(String keyword) {
		
		List<Board> board = boardRepo.findByTitleContaining(keyword);
		
		List<BoardForm> boardForm = new ArrayList<>();
		
		if(board.isEmpty()) return boardForm;
		
		for(Board boardEntity: board) {
			boardForm.add(this.convertEntityToForm(boardEntity));
		}
		
		return boardForm;
		
	}
	
	private BoardForm convertEntityToForm(Board board) {
		return BoardForm.builder()
				.id(board.getId())
				.writer(board.getWriter())
				.title(board.getTitle())
				.content(board.getContent())		
				.created_at(board.getCreated_at())
				.modified_at(board.getModified_at())
				.build();
	}
	
	

}
