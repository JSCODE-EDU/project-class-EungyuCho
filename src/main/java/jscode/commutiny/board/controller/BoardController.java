package jscode.commutiny.board.controller;

import jscode.commutiny.board.dto.BoardDto;
import jscode.commutiny.board.model.Board;
import jscode.commutiny.board.repository.BoardRepository;
import jscode.commutiny.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("board")
public class BoardController {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardController(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    @GetMapping()
    public ResponseEntity<Iterable<BoardDto>> getBoards() {
        Iterable<Board> boards = boardRepository.findAll();

        List<BoardDto> boardDtoList = new ArrayList<>();
        for (Board board : boards) {
            boardDtoList.add(board.toDto());
        }

        return ResponseEntity.ok(boardDtoList);
    }

    @PostMapping()
    public ResponseEntity<BoardDto> createBoard(@RequestBody BoardDto boardDto) {
        Board board = new Board();
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());

        Board savedBoard = boardRepository.save(board);

        return ResponseEntity.ok(savedBoard.toDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardDto> getBoard(@PathVariable Long id) throws ResourceNotFoundException {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board", "id", id));

        return ResponseEntity.ok(board.toDto());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardDto> updateBoard(@PathVariable Long id, @RequestBody BoardDto boardDto) throws ResourceNotFoundException {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board", "id", id));

        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());

        Board savedBoard = boardRepository.save(board);

        return ResponseEntity.ok(savedBoard.toDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id) throws ResourceNotFoundException {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board", "id", id));

        boardRepository.delete(board);

        return ResponseEntity.ok().build();
    }

}