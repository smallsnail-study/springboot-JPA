package com.example.b01.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // 데이터에 해당하는 객체
public class Board {

    @Id // 엔티티 객체의 구분
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 데이터베이스에 추가될 때 생성되는 번호 이용 키 생성 전략
    private Long bno;

    private String title;

    private String content;

    private String writer;

}
