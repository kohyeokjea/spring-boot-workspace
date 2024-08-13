package com.example.ex16_jpa_board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "simple_board")
public class Board {
    @Id // key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가
    private int id;
    private String writer;
    private String title;
    private String content;
}
