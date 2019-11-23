/*package com.filternews.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

	public Category() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "category")
	private List<News> all_news;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<News> getAll_news() {
		return all_news;
	}

	public void setAll_news(List<News> all_news) {
		this.all_news = all_news;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", all_news=" + all_news + "]";
	}
	
	
}
*/
