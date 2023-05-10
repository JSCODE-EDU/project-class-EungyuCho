package board.model;

import board.dto.BoardDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public Board() { }

    public Board(Builder builder) {
        id = builder.id;
        title = builder.title;
        content = builder.content;
    }


    public static Builder builder() {
        return new Builder();
    }

    public BoardDto toDto() {
        return BoardDto.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
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

        public Board build() {
            return new Board(this);
        }
    }
}
