package com.filternews.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filternews.entity.News;

public interface NewsRepository extends JpaRepository<News, Integer> {

	public List<News> findAllByOrderByCreatedDateDesc();
	
	public List<News> findByIsActiveOrderByCreatedDateDesc(Boolean value);

	public List<News> findByIsActiveOrderByNumVotesDesc(Boolean b);
	
}
