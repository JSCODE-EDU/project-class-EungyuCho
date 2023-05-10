package board.controller;

import board.dto.BoardDto;
import board.model.Board;
import board.repository.BoardRepository;
import common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardController(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    // 특정 게시글 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<BoardDto> getBoard(@PathVariable Long id) throws ResourceNotFoundException {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board", "id", id));

        return ResponseEntity.ok(board.toDto());
    }

    // 특정 게시글 수정 API
    @PutMapping("/{id}")
    public ResponseEntity<BoardDto> updateBoard(@PathVariable Long id, @RequestBody BoardDto boardDto) throws ResourceNotFoundException {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board", "id", id));

        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());

        Board savedBoard = boardRepository.save(board);

        return ResponseEntity.ok(savedBoard.toDto());
    }

    // 특정 게시글 삭제 API
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id) throws ResourceNotFoundException {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board", "id", id));

        boardRepository.delete(board);

        return ResponseEntity.ok().build();
    }

}