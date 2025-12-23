package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Comment extends TimeBase{
    // 테이블 생성시 ID필수임

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cno;
    @Column(nullable = false)
    private Long bno;
    @Column(length = 200 , nullable = false)
    private String writer;
    private String content;
}
