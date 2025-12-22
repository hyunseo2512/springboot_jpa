package com.example.demo.repository;

import com.example.demo.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

// jpa 기능을 처리하느 인터페이스
// JpaRepository 상속 받아서 사용
// JpaRepository<테이블명, id Type> id Type : 클래스 형태여야함
public interface BoardRepository extends JpaRepository<Board, Long> {
    // 일반 DB 사용시 repository에서 할 일은 없음.

}
