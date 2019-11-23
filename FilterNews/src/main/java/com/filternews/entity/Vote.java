package com.filternews.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "vote")
public class Vote {

	public Vote() {}
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@OneToOne()
	@JoinColumn(name = "user")
	private User user;
	
	@OneToOne()
	@JoinColumn(name = "news")
	private News news;
	
	@Column(name = "is_up")
	private Boolean isUp;
	
	@Column(name = "credibility_score")
	private Float credibilityScore;
	
	@Column(name = "date_of_creation")
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private LocalDateTime createdDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Boolean getIsUp() {
		return isUp;
	}

	public void setIsUp(Boolean isUp) {
		this.isUp = isUp;
	}

	public Float getCredibilityScore() {
		return credibilityScore;
	}

	public void setCredibilityScore(Float float1) {
		this.credibilityScore = float1;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime localDateTime) {
		this.createdDate = localDateTime;
	}

	@Override
	public String toString() {
		return "Vote [id=" + id + ", user=" + user + ", news=" + news + ", is_up=" + isUp + ", credibilityScore="
				+ credibilityScore + ", createdDate=" + createdDate + "]";
	}
	
	
	
	
}