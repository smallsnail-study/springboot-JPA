package com.example.b01.domain;

import lombok.*;

import javax.persistence.*;

@Entity // 데이터에 해당하는 객체
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Board extends BaseEntity{

    @Id // 엔티티 객체의 구분
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 데이터베이스에 추가될 때 생성되는 번호 이용 키 생성 전략
    private Long bno;

    @Column(length = 500, nullable = false) // 칼럼의 길이와 null허용여부
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String writer;

    // 수정이 가능한 제목/내용 부분을 미리 메소드로 설계한다.
    public void change(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
