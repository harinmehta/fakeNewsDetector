package com.filternews.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {

	public Comment() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "comment_data")
	private String commentData;
	
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne()
	@JoinColumn(name = "news_id")
	private News news;
	
	public Comment(String commentData) {
		this.commentData = commentData;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCommentData() {
		return commentData;
	}

	public void setCommentData(String commentData) {
		this.commentData = commentData;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", commentData=" + commentData + "]";
	}
	
	
}
