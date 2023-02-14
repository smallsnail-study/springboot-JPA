package com.example.b01.repository.search;

import com.example.b01.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {
    // 인터페이스이름+Impl 이름으로 클래스를 선언 - QuerydslRepositorySupport란느 부모 클래스를 지정하고 인터페이스를 구현

    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {
        return null;
    }
}
