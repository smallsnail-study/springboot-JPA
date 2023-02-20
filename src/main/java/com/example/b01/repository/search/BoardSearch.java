package com.example.b01.repository.search;

import com.example.b01.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {
    // Querydsl을 이용할 인터페이스 선언

    Page<Board> search1(Pageable pageable); // 단순히 페이지 처리 기능만을 선언

    // 검색을 위한 검색조건들(여러조건의 조합이 가능)과 키워드를 처리하는 메소드 선언
    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);

}
