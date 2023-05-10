package jscode.commutiny;

import board.model.Board;
import board.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@DataJpaTest
public class BoardControllerTest {

    private BoardRepository boardRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @DisplayName("게시판 글 작성")
    public void testSaveBoard() {
        Board board = new Board();
        board.setTitle("후라이드 맛있는 집");
        board.setContent("네네치킨");
        boardRepository.save(board);

        Board found = entityManager.find(Board.class, 1L);
        assertNotNull(found);
        assertEquals(board.getTitle(), found.getTitle());
        assertEquals(board.getContent(), found.getContent());
    }

    @Test
    @DisplayName("게시판 조회 테스트")
    public void testFindAllBoards() {
        Board board1 = new Board();
        board1.setTitle("후라이드 맛있는 집");
        board1.setContent("네네치킨");
        boardRepository.save(board1);

        Board board2 = new Board();
        board2.setTitle("양념 맛있는 집");
        board2.setContent("처갓집");
        boardRepository.save(board2);

        List<Board> boardList = boardRepository.findAll();

        assertEquals(boardList.size(), 2);

        Board found1 = entityManager.find(Board.class, 1L);
        assertNotNull(found1);
        assertEquals(boardList.get(0).getTitle(), found1.getTitle());
        assertEquals(boardList.get(0).getContent(), found1.getContent());

        Board found2 = entityManager.find(Board.class, 2L);
        assertNotNull(found2);
        assertEquals(boardList.get(1).getTitle(), found2.getTitle());
        assertEquals(boardList.get(1).getContent(), found2.getContent());
    }

    @Test
    @DisplayName("글 삭제")
    public void testDeleteBoard() {
        Board board = new Board();
        board.setTitle("후라이드 잘하는 집");
        board.setContent("네네치킨");
        boardRepository.save(board);

        List<Board> boardList = boardRepository.findAll();
        assertEquals(boardList.size(), 1);

        boardRepository.delete(board);

        boardList = boardRepository.findAll();
        assertEquals(boardList.size(), 0);
    }
}