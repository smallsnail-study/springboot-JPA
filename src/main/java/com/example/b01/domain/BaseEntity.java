package com.example.b01.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass // 모든 테이블에서 공통으로 사용되는 칼럼들을 지정
@EntityListeners(value = {AuditingEntityListener.class})// 엔티티가 데이터베이스에 추가되거나 변경될 때 자동으로 지정 가능
@Getter
public class BaseEntity {

    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;  // 데이터가 추가된 시간

    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;  // 데이터가 수정된 시간
}
