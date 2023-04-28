package com.example.b01.service;

import com.example.b01.dto.BoardDTO;
import com.example.b01.dto.PageRequestDTO;
import com.example.b01.dto.PageResponseDTO;

public interface BoardService {

    // 등록
    Long register(BoardDTO boardDTO);

    // 조회작업을 위한 게시물 번호
    BoardDTO readOne(Long bno);

    // 수정작업 처리
    void modify(BoardDTO boardDTO);

    // 삭제
    void remove(Long bno);

    // 목록/검색
    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
