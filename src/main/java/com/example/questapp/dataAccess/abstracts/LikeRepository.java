package com.example.questapp.dataAccess.abstracts;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.questapp.entities.concretes.Like;

public interface LikeRepository extends JpaRepository<Like, Long>{

	List<Like> findByUserId(Long id);

	List<Like> findByPostId(Long id);

}
