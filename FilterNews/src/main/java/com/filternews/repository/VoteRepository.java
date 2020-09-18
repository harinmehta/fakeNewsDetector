package com.filternews.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filternews.entity.News;
import com.filternews.entity.User;
import com.filternews.entity.Vote;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

	List<Vote> findByNewsAndIsUp(News news, Boolean isUp);
	
	Vote findByUserAndNews(User user, News news); 
	
}
