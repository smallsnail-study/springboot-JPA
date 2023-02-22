package com.example.b01.service;

import com.example.b01.dto.BoardDTO;

public interface BoardService {

    Long register(BoardDTO boardDTO);

    // 조회작업을 위한 게시물 번호
    BoardDTO readOne(Long bno);
}
