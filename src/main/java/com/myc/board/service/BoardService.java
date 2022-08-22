package com.myc.board.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myc.board.domain.Board;
import com.myc.board.domain.BoardRepository;
import com.myc.board.dto.BoardRequestDto;
import com.myc.board.dto.BoardResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardRequestDto boardRequestDto) {
        return boardRepository.save(boardRequestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<BoardResponseDto> findByPage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Board> boardListPage = boardRepository.findAll(pageRequest);
        return boardListPage.stream().map(BoardResponseDto::new)
                                    .collect(Collectors.toList());
    }

    public BoardResponseDto findById(Long id) {
        return new BoardResponseDto(boardRepository.findById(id).get());
    }

    @Transactional
    public Long updaetBoard(Long id, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(id)
                                    .orElseThrow();
        board.update(boardRequestDto.getTitle(), 
                        boardRequestDto.getContent(), 
                        boardRequestDto.getCategory());
        return new BoardResponseDto(board).getId();
    }

    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }
    
}
