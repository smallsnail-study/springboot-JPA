package com.example.b01.repository;

import com.example.b01.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
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

    @Test
    public void testDelete() {
        Long bno = 1L;

        boardRepository.deleteById(bno);
    }

    @Test
    public void testPaging() {

        // 1 page order by bno desc
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        //Pageable의 리턴타입 Page<T> 목록처리, count처리 자동실행가능(이전페이지,다음페이지가 존재하는지, 전체데이터개수가 몇개인지)
        Page<Board> result = boardRepository.findAll(pageable);

        log.info("total count: " + result.getTotalElements());
        log.info("total pages: " + result.getTotalPages());
        log.info("page number: " + result.getNumber());
        log.info("page size: " + result.getSize());

        List<Board> todoList = result.getContent();

        todoList.forEach(board -> log.info(board));
    }

    @Test
    public void testSearch1() {
        // 2 page order by bno desc
        Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());

        boardRepository.search1(pageable);
    }

    @Test
    public void testSearchALl() {

        // 검색조건인 types는 '제목(t),내용(c),작성자(w)'로 구성
        String[] types = {"t", "c", "w"};

        String keyword = "1";

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);
    }

    @Test
    public void testSearchALl2() {

        String[] types = {"t", "c", "w"};

        String keyword = "1";

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);

        // total pages
        log.info(result.getTotalPages());

        // page size
        log.info(result.getSize());

        // pageNumber
        log.info(result.getNumber());

        // prev next
        log.info(result.hasPrevious() + ": " + result.hasNext());

        result.getContent().forEach(board -> log.info(board));

        // 테스트결과 : 키워드 '1'이라는 글자가 있는 총 페이지 수는 2이고, 이전페이지(prev)는 없지만 다음페이지(next)는 존재
        //2023-02-21 00:00:57.926  INFO 25596 --- [    Test worker] c.e.b01.repository.BoardRepositoryTests  : 2
        //2023-02-21 00:00:57.927  INFO 25596 --- [    Test worker] c.e.b01.repository.BoardRepositoryTests  : 10
        //2023-02-21 00:00:57.927  INFO 25596 --- [    Test worker] c.e.b01.repository.BoardRepositoryTests  : 0
        //2023-02-21 00:00:57.932  INFO 25596 --- [    Test worker] c.e.b01.repository.BoardRepositoryTests  : false: true
    }
}
