package com.filternews.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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

import com.fasterxml.jackson.annotation.JsonFormat;

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
	
//	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
//	@JoinColumn(name = "news_category")
//	private Category category;
	
	@Column(name = "num_comments")
	private Integer numComments;
//	
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinColumn(name = "comment_id")
//	private List<Comment> comments;
	
	@Column(name = "headLine")
	private String headLine;
	
	@Column(name = "num_votes")
	private Integer numVotes;
	
	// not adding image now
	
	public Integer getNumVotes() {
		return numVotes;
	}

	public void setNumVotes(Integer numVotes) {
		this.numVotes = numVotes;
	}

	@Column(name = "is_active")
	private Boolean isActive;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "news_data")
	private String newsData;
	
	@Column(name = "result")
	private Boolean result;
	
	@Column(name = "date_of_creation")
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private LocalDateTime createdDate;
	
	@Column(name = "no_of_views")
	private Integer noOfViews;
	
	@Column(name = "image_name")
	private String image_name;
	
	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime localDateTime) {
		this.createdDate = localDateTime;
	}

	public String getImage_name() {
		return image_name;
	}

	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

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
	
//	public Category getCategory() {
//		return category;
//	}
//
//	public void setCategory(Category category) {
//		this.category = category;
//	}

	public Integer getNoOfViews() {
		return noOfViews;
	}

	public void setNoOfViews(Integer noOfViews) {
		this.noOfViews = noOfViews;
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

//	public List<Comment> getComments() {
//		return comments;
//	}
//
//	public void setComments(List<Comment> comments) {
//		this.comments = comments;
//	}
//	
//	public void addComment(Comment newComment) {
//		
//		if(comments == null) {
//			comments = new ArrayList<>();
//		}
//		
//		comments.add(newComment);
//	}

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
		return "News [id=" + id + ", tags=" + tags + ", numComments=" + numComments 
				+ ", headLine=" + headLine + ", isActive=" + isActive + ", user=" + user + ", newsData=" + newsData
				+ "]";
	}

}
