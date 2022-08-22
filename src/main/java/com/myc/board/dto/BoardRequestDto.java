package com.myc.board.dto;

import com.myc.board.domain.Board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardRequestDto {
    private Long id;
    private String title;
    private String content;
    private String category;
    private String email;
    private String user;
    private int views;
    private char delete_yn;
    private int thumbUp;
    private int thumbDown;

    public Board toEntity() {
        return Board.builder()
                            .title(title)
                            .content(content)
                            .category(category)
                            .email(email)
                            .user(user)
                            .views(views)
                            .delete_yn(delete_yn)
                            .thumbDown(thumbDown)
                            .thumbUp(thumbUp)
                            .build();
    }
}
