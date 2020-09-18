package com.filternews.model;

import java.time.LocalDateTime;

public class UserRequestModel {
	
	private String commentData;
	
	private Integer userId;						        
							        
	private Integer newsId;						        
							        
	private LocalDateTime commentDate;

	private Integer upVote;

	

	public Integer getUpVote() {
		return upVote;
	}

	public void setUpVote(Integer upVote) {
		this.upVote = upVote;
	}

	public String getCommentData() {
		return commentData;
	}

	public void setCommentData(String commentData) {
		this.commentData = commentData;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public LocalDateTime getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(LocalDateTime commentDate) {
		this.commentDate = commentDate;
	}
	
	
}
