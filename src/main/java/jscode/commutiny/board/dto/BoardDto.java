package jscode.commutiny.board.dto;

import jscode.commutiny.board.model.Board;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDto {

    private Long id;
    private String title;
    private String content;

    public BoardDto(Builder builder) {
        id = builder.id;
        title = builder.title;
        content = builder.content;
    }

    public BoardDto() {
    }

    public Board toEntity() {
        return Board.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        Long id;
        String title;
        String content;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public BoardDto build() {
            return new BoardDto(this);
        }
    }
}