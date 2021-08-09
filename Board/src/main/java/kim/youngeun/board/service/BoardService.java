package kim.youngeun.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kim.youngeun.board.model.Board;
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
	
	public Board findByno(Integer id) {
		
		return boardRepo.findById(id).orElse(null);
	}
	
	public Board getBoardId(int id) {
		
		return boardRepo.getById(id);
	}

	
	public void updateBoard(Board board) {

		boardRepo.save(board);
	}
	
	public void deleteBoard(int id) {
		
		boardRepo.deleteById(id);
	}
	
	

}
