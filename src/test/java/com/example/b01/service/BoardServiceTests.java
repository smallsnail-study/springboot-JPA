package com.example.b01.service;

import com.example.b01.dto.BoardDTO;
import com.example.b01.repository.BoardRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister() {

        // boardService변수가 가리키는 객체의 클래스명을 출력
        // 그러나 실행해보면 BoardServiceImpl이 나오지 않고 스프링에서 BoardServiceImpl을 감싸서 만든 클래스 정보가 출력
        //com.example.b01.service.BoardServiceImpl$$EnhancerBySpringCGLIB$$50183ac2
        log.info(boardService.getClass().getName());

        BoardDTO boardDTO = BoardDTO.builder()
                .title("Sample Title...")
                .content("Sample Content...")
                .writer("user00")
                .build();

        Long bno = boardService.register(boardDTO);

        log.info("bno: " + bno);
    }

    @Test
    public void testModify() {

        // 변경에 필요한 데이터만
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(101L)
                .title("Updated....101")
                .content("Updated content 101...")
                .build();

        boardService.modify(boardDTO);
    }
}
