package com.example.ex16_jpa_board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.ex16_jpa_board.entity.Board;
import com.example.ex16_jpa_board.repository.BoardRepository;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public List<Board> list() {
        return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Board view(int id) {
        return boardRepository.findById(id).orElse(null);
    }

    // public void write(String write, String title, String content) {
    // Board board = new Board();

    // board.setWriter(write);
    // board.setTitle(title);
    // board.setContent(content);

    // boardRepository.save(board);
    // }

    public void write(String write, String title, String content) {
        boardRepository.write(write, title, content);
    }

    public void delete(int id) {
        boardRepository.deleteById(id);
    }
}
