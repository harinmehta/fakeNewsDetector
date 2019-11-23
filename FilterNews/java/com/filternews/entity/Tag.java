package com.filternews.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tag")
public class Tag {

	public Tag() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "news_tag",
			   joinColumns = @JoinColumn(name = "tag_id"),
			   inverseJoinColumns = @JoinColumn(name ="news_id"))
	private List<News> allNews;
	
	@Column(name = "tag_name")
	private String tagName;
	
	public Tag(String tagName) {
		this.tagName = tagName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<News> getAllNews() {
		return allNews;
	}

	public void setAllNews(List<News> allNews) {
		this.allNews = allNews;
	}

	public void addNews(News newNews) {
		
		if(allNews == null) {
			allNews = new ArrayList<>();
		}
		
		allNews.add(newNews);
	}
	
	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", allNews=" + allNews + ", tagName=" + tagName + "]";
	}
	
	
}
