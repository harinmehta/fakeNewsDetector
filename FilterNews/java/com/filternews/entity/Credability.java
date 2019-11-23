package com.filternews.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "credability")
public class Credability {

	public Credability() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "up_votes")
	private Integer upVotes;
	
	@Column(name = "down_votes")
	private Integer downVotes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUpVotes() {
		return upVotes;
	}

	public void setUpVotes(Integer upVotes) {
		this.upVotes = upVotes;
	}

	public Integer getDownVotes() {
		return downVotes;
	}

	public void setDownVotes(Integer downVotes) {
		this.downVotes = downVotes;
	}

	@Override
	public String toString() {
		return "Credability [id=" + id + ", upVotes=" + upVotes + ", downVotes=" + downVotes + "]";
	}
	
}
