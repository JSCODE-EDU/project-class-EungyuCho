package jscode.commutiny.board.model;

import com.sun.istack.NotNull;
import jscode.commutiny.board.dto.BoardDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(force = true)
@ToString
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String title;

    @NotNull
    @Column
    private String content;

    public Board(@NotNull String title, @NotNull String content) {
        this.title = title;
        this.content = content;
    }
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
