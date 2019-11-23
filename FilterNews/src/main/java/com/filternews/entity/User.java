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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
//	@Column(name = "is_verified")
//	private Boolean isVerified;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone_no")
	private String phoneNo;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "state")
	private String state;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "credability_id")
	private Credability credability;
	
	@Column(name = "credability_score")
	private Float credabilityScore;
	
//	@OneToMany(fetch = FetchType.LAZY,mappedBy = "user", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//	private List<News> allNews;
	
	public User() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

/*	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}
*/
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Credability getCredability() {
		return credability;
	}

	public void setCredability(Credability credability) {
		this.credability = credability;
	}

	public Float getCredabilityScore() {
		return credabilityScore;
	}

	public void setCredabilityScore(Float credabilityScore) {
		this.credabilityScore = credabilityScore;
	}

//	public List<News> getAllNews() {
//		return allNews;
//	}
//
//	public void setAllNews(List<News> allNews) {
//		this.allNews = allNews;
//	}
	
//	public void addNews(News newNews) {
//		
//		if(allNews == null) {
//			allNews = new ArrayList<>();
//		}
//		
//		allNews.add(newNews);
//	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" 
				+ email + ", phoneNo=" + phoneNo + ", password=" + password + ", country=" + country
				+ ", state=" + state + ", credability=" + credability + ", credabilityScore=" + 	
				credabilityScore ;
	}
	
}
