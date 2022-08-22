package com.myc.board.dto;

import java.time.LocalDateTime;

import com.myc.board.domain.Board;

import lombok.Getter;

@Getter
public class BoardResponseDto {
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
    private LocalDateTime createTime;

    public BoardResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.category = entity.getCategory();
        this.email = entity.getEmail();
        this.user = entity.getUser();
        this.views = entity.getViews();
        this.delete_yn = entity.getDelete_yn();
        this.createTime = entity.getCreateTime();
        this.thumbUp = entity.getThumbUp();
        this.thumbDown = entity.getThumbDown();
    }

    // public String toString() {
    //     return "BoardListDto [id="
    // }
}
