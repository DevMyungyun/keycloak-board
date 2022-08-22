package com.myc.board.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.MODULE.PROTECTED)
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length=300)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
    @Column(nullable = false, length = 50)
    private String category;
    @Column(nullable = false, length = 50)
    private String email;
    @Column(nullable = false, length = 50)
    private String user;
    // @Column(length = 30)
    // private String create_dt;
    // @Column(length = 200)
    // private String update_dt;
    @Column(nullable = false)
    private int views;
    @Column(nullable = false)
    private char delete_yn = 'N';
    @Column(nullable = false)
    private int thumbUp;
    @Column(nullable = false)
    private int thumbDown;

    @Builder
    public Board(String title, String content, String category, 
                        String email, String user, int views, 
                        char delete_yn, int thumbUp, int thumbDown) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.email = email;
        this.user = user;
        this.views = views;
        this.delete_yn = delete_yn;
        this.thumbUp = thumbUp;
        this.thumbDown = thumbDown;
    }

    public void update(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
