package com.example.b01.repository;

import com.example.b01.domain.Board;
import com.example.b01.repository.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {   // JpaRepository를 상속할 때는 <엔티티타입,@Id타입>을 지정해야한다.
    // JpaRepository 인터페이스를 상속하는 인터페이스를 선언하는 것만으로도 데이터베이스 관련 작업 처리 가능

    // 기존의 Repository에 부모 인터페이스로 Querydsl을 위한 인터페이스를 지정
    @Query(value = "select now()", nativeQuery = true)
    String getTime();
}
