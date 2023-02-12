package com.example.b01.repository;

import com.example.b01.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Board board = Board.builder()
                    .title("title..." + i)
                    .content("content..." + i)
                    .writer("user" + (i % 10))
                    .build();

            // 데이터베이스에 insert를 실행하는 기능은  JpaRepository의 save()를 통해서 이루어진다.
            // save()는 현재 영속 컨텍스트 내에 데이터가 존재하는지 찾아보고 해당 엔티티 객체가 없을 때는 insert, 존재할때는 update 자동실핼
            Board result = boardRepository.save(board);
            log.info("BNO: " + result.getBno());
        });
    }

    @Test
    public void testSelect() {  // 특정 게시물 조회

        Long bno = 100L;

        // 특정한 번호의 게시물을 조회하는 기능은 findById()를 이용해서 처리한다.
        // findById()의 리턴 타입은 Optional<T>이다.
        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow();

        log.info(board);
    }

    @Test
    public void testUpdate() {
        Long bno = 100L;

        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow();

        board.change("update..title 100", "update content 100");

        boardRepository.save(board);
    }
}
