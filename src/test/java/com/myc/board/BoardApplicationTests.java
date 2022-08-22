package com.myc.board;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myc.board.domain.Board;
import com.myc.board.domain.BoardRepository;
import com.myc.board.dto.BoardRequestDto;
import com.myc.board.dto.BoardResponseDto;
import com.myc.board.service.BoardService;


@SpringBootTest
class BoardApplicationTests {

	@Autowired
	private BoardService boardService;

	@Test
	void contextLoads() {
	}

	@Test
	void save() {
		BoardRequestDto boardRequestDto = new BoardRequestDto();

		for(int i=0; i < 100; i++) {
			boardRequestDto.setTitle("TEST TITLE "+i);
			boardRequestDto.setContent("TEST content ====== > " + i);
			if(i < 50 ) {
				boardRequestDto.setCategory("CATEGORY111");
				boardRequestDto.setEmail("manager01@test.com");
				boardRequestDto.setUser("manager01");
			} else {
				boardRequestDto.setCategory("CATEGORY222");
				boardRequestDto.setEmail("user01@test.com");
				boardRequestDto.setUser("user01");
			} 			
			boardRequestDto.setThumbDown(0);
			boardRequestDto.setThumbUp(0);
			boardRequestDto.setViews(0);
			try {
				boardService.save(boardRequestDto);	
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
		
	}

	@Test
	void findByPage() {
		int page = 0, size = 10;
		List<BoardResponseDto> result = boardService.findByPage(page, size);
		result.stream().forEach(val -> System.out.printf("%d : %s \n", val.getId(), val.getTitle()));
	}

	@Test
	void findById() {
		BoardResponseDto boardResponseDto = boardService.findById(Long.valueOf(101));
		System.out.println(boardResponseDto.getTitle());
		assertEquals("TEST TITLE 0", boardResponseDto.getTitle());
	}

	@Test
	void updaetBoard() {
		BoardRequestDto boardRequestDto = new BoardRequestDto();

		// boardRequestDto.setId(Long.valueOf(102));
		boardRequestDto.setTitle("TEST TITLE 1 UPDATE");
		boardRequestDto.setContent("TEST content ====== > 1 UPDATE !!!");
		boardRequestDto.setCategory(	"CATEGORY111-UPDATE");
		
		Long id = boardService.updaetBoard(
												Long.valueOf(102), boardRequestDto) ;

		System.out.println(id);
		assertEquals(102, id);
	}
}
