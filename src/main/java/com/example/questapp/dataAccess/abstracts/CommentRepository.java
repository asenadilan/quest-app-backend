package com.example.questapp.dataAccess.abstracts;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.questapp.entities.concretes.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	List<Comment> findByUserId(Long userId);

	List<Comment> findByPostId(Long postId);

}
