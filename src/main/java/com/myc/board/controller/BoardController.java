package com.myc.board.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myc.board.dto.BoardRequestDto;
import com.myc.board.dto.BoardResponseDto;
import com.myc.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello Board!";
    }

    @PostMapping()
    @ResponseBody
    public ResponseEntity<Long> save(@RequestBody BoardRequestDto board) {
        return ResponseEntity.ok().body(boardService.save(board));
    }

    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<List<BoardResponseDto>> findByPage(
                                                    @RequestParam(name = "page") int page, 
                                                    @RequestParam(name = "size") int size) {
        return ResponseEntity.ok()
                            .body(boardService.findByPage(page, size));
    }

    @GetMapping("/list/detail")
    @ResponseBody
    public ResponseEntity<BoardResponseDto> findById(@RequestParam(name = "page") int id) {
        return ResponseEntity.ok()
                    .body(boardService.findById(Long.valueOf(id)));
    }

    @PatchMapping
    @ResponseBody
    public ResponseEntity<Long> updateBoard(@RequestParam(name = "id") int id, @RequestBody BoardRequestDto board) {
        return ResponseEntity.ok()
                            .body(boardService.updaetBoard(Long.valueOf(id), board));
    }
    
    @DeleteMapping
    @ResponseBody
    public ResponseEntity<Integer> deleteBoard(@RequestParam(name = "id") int id) {
        return ResponseEntity.ok()
                            .body(id);
    }

} 