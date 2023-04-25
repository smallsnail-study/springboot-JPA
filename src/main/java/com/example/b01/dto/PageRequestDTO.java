package com.example.b01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {   //페이징(page/size),검색종류(type),키워드(keyword) 추가 지정

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

    // 문자열 하나로 처리해서 나중에 각 문자를 분리
    private String type;    // 검색종류 t,c,w,tc,tw,twc

    private String keyword;

    // 검색 조건들을 String[]로 처리하므로 type이라는 문자열을 배열로 반환해 주는 기능 필요
    public String[] getTypes() {
        if (type == null || type.isEmpty()) {
            return null;
        }
        return type.split("");
    }

    // 페이징 처리 Pageable 타입을 반환하는 기능
    public Pageable getPageable(String... props) {
        return PageRequest.of(this.page - 1, this.size, Sort.by(props).descending());
    }

    // 검색 조건,페이징 조건을 문자열로 구성
    private String link;

    public String getLink() {
        if (link == null) {
            StringBuilder builder = new StringBuilder();

            builder.append("page=" + this.page);
            builder.append("size=" + this.size);

            if (type != null && type.length() > 0) {
                builder.append("&type=" + type);
            }

            if (keyword != null) {
                try {
                    builder.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                }
            }
            link = builder.toString();
        }
        return link;
    }
}
