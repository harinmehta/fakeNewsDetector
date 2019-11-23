package com.filternews.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filternews.entity.Comment;
import com.filternews.entity.News;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

	List<Comment> findByNews(News news);
	
}
