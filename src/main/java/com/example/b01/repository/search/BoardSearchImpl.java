package com.example.b01.repository.search;

import com.example.b01.domain.Board;
import com.example.b01.domain.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {
    // 인터페이스이름+Impl 이름으로 클래스를 선언 - QuerydslRepositorySupport라는 부모 클래스를 지정하고 인터페이스를 구현

    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {
        // Querrydsl의 목적은 타입 기반으로 코드를 이용해서 JPQL 쿼리를 생성하고 실행
        // Q도메인 클래스 이용(코드를 만드는 대신 클래스)

        QBoard board = QBoard.board;    // Q도메인 객체

        JPQLQuery<Board> query = from(board);   // select.. from board

        // where 조건에 and와 or이 섞여 있을 때 연산자의 우선 순위가 다르므로 or 조건은 ()로 묶어서 하나의 단위로 만들어 주는 것이 좋다.
        // Querydsl 이용 시 ()가 필요한 상황에서는 BooleanBuilder를 이용해서 작성 가능
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        // 제목 or 내용에 대한 처리를 BooleanBuilder에 or()를 이용해서 추가
        booleanBuilder.or(board.title.contains("11"));  // title like ...

        booleanBuilder.or(board.content.contains("11"));    // content like ...

        query.where(booleanBuilder);

        query.where(board.bno.gt(0L));

//        query.where(board.title.contains("1")); // where title like ...

        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();   // JPQLQuery의 실행은 fetch()라는 기능을 이용

        long count = query.fetchCount();    // fetchCount()를 이용하면 count쿼리 실행 가능

        return null;
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {

        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        // 검색 조건과 키워드가 있다면
        if ((types != null && types.length > 0) && keyword != null) {

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            // 검색조건인 types는 '제목(t),내용(c),작성자(w)'로 구성
            for (String type : types) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }

        // bno > 0
        query.where(board.bno.gt(0L));

        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();

        long count = query.fetchCount();

//        return null;
        // 페이징 처리의 최종결과는 Page<T>타입을 반환, Querydsl에서 직접처리하는 불편함
        // Spring Data JPA에서는 이 처리를 위해 PageImpl이라는 클래스를 제공해서 3개의 파라미터로 Page<T>생성 가능
        /* List<T> : 실제 목록 데이터
           Pageable : 페이지 관련 정보를 가진 객체
           long : 전체 개수
         */
        return new PageImpl<>(list, pageable, count);
    }
}
