package jscode.commutiny.board.repository;

import jscode.commutiny.board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}