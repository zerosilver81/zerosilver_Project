package kim.youngeun.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kim.youngeun.board.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
