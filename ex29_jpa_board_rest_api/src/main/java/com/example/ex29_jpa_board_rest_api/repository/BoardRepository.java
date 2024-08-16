package com.example.ex29_jpa_board_rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ex29_jpa_board_rest_api.entity.Board;

import jakarta.transaction.Transactional;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    @Modifying
    @Transactional
    @Query("insert into Board(writer, title, content) values (:w, :t, :c)")
    void write(@Param("w") String writer, @Param("t") String title, @Param("c") String content);
}
