package kim.youngeun.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kim.youngeun.board.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	List<Board> findByTitleContaining(String keyword);
}
