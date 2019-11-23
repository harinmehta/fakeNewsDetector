package com.filternews.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "news")
public class News {
	
	public News() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "news_tag",
			   joinColumns = @JoinColumn(name = "news_id"),
			   inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<Tag> tags;
	
	@Column(name = "num_comments")
	private Integer numComments;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "comment_id")
	private List<Comment> comments;
	
	@Column(name = "headLine")
	private String headLine;
	
	// not adding image now
	
	@Column(name = "is_active")
	private Boolean isActive;
	
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "news_data")
	private String newsData;
	
	@Column(name = "result")
	private Boolean result;
	
	
	//private Date createdDate;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	public void addTag(Tag newTag) {
		
		if(tags == null) {
			tags = new ArrayList<>();
		}
		
		tags.add(newTag);
	}

	public Integer getNumComments() {
		return numComments;
	}

	public void setNumComments(Integer numComments) {
		this.numComments = numComments;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public void addComment(Comment newComment) {
		
		if(comments == null) {
			comments = new ArrayList<>();
		}
		
		comments.add(newComment);
	}

	public String getHeadLine() {
		return headLine;
	}

	public void setHeadLine(String headLine) {
		this.headLine = headLine;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNewsData() {
		return newsData;
	}

	public void setNewsData(String newsData) {
		this.newsData = newsData;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", tags=" + tags + ", numComments=" + numComments + ", comments=" + comments
				+ ", headLine=" + headLine + ", isActive=" + isActive + ", user=" + user + ", newsData=" + newsData
				+ "]";
	}

}
