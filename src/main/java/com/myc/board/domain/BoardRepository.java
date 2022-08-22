package com.myc.board.domain;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.myc.board.dto.BoardRequestDto;

public interface BoardRepository extends JpaRepository<Board, Long> {
    
    final String UPDATE_BOARD = "UPDATE board " +
                                    "SET TITLE = :#{#boardRequestDto.title}, " +
                                    "content = :#{#boardRequestDto.content}, " +
                                    "category = :#{#boardRequestDto.category}, " +
                                    "UPDATE_TIME = NOW() " +
                                    "WHERE ID = :#{#boardRequestDto.id}";
    
    @Transactional
    @Modifying
    @Query(value = UPDATE_BOARD, nativeQuery = true)
    public int updaetBoard(@Param("boardRequestDto") BoardRequestDto boardRequestDto);
    
}
