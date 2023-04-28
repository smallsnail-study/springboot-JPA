package com.example.b01.controller;

import com.example.b01.dto.PageRequestDTO;
import com.example.b01.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 목록기능
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {

    }
}
