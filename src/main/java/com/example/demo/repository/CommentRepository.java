package com.example.demo.repository;

import com.example.demo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<Entity, Id Class>
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
