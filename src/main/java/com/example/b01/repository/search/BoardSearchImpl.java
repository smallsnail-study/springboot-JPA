package com.example.b01.repository.search;

import com.example.b01.domain.Board;
import com.example.b01.domain.QBoard;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {
    // 인터페이스이름+Impl 이름으로 클래스를 선언 - QuerydslRepositorySupport란느 부모 클래스를 지정하고 인터페이스를 구현

    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {
        // Querrydsl의 목적은 타입 기반으로 코드를 이용해서 JPQL 쿼리를 생성하고 실행
        // Q도메인 클래스 이용(코드를 만드는 대신 클래스)

        QBoard board = QBoard.board;    // Q도메인 객체

        JPQLQuery<Board> query = from(board);   // select.. from board

        query.where(board.title.contains("1")); // where title like ...

        List<Board> list = query.fetch();   // JPQLQuery의 실행은 fetch()라는 기능을 이용

        long count = query.fetchCount();    // fetchCount()를 이용하면 count쿼리 실행 가능

        return null;
    }
}
