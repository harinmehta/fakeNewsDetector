package com.filternews.controller;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filternews.dto.Response;
import com.filternews.entity.Comment;
import com.filternews.entity.News;
import com.filternews.entity.User;
import com.filternews.entity.Vote;
import com.filternews.model.UserRequestModel;
import com.filternews.repository.CommentRepository;
import com.filternews.repository.NewsRepository;
import com.filternews.repository.UserRepository;
import com.filternews.repository.VoteRepository;
import com.filternews.service.UserServiceImpl;
import com.filternews.util.JsonUtill;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	NewsRepository newsRepository;
	
	@Autowired
	VoteRepository voteRepository;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	CommentRepository commentRepository;
	
	@PostMapping("/register")
	public ResponseEntity<Integer> registerNewUser(@RequestBody User user,BindingResult errors){
		Response<Integer> response = null;
		logger.info("Inside registerNewUser()");
		if(errors.hasErrors()) {			
			logger.error("Invalid request!..caught by Binding Result");
			response =new Response<Integer>( 2001,"Request body is not valid with reason",null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		if(StringUtils.isEmpty(user.getFirstName()) || StringUtils.isEmpty(user.getPassword())|| 
				StringUtils.isEmpty(user.getLastName()) || (StringUtils.isEmpty(user.getEmail()))) {
//			if(StringUtils.isEmpty(user.getEmail())) logger.info("email is empty");
//			if(StringUtils.isEmpty(user.getFirstName())) logger.info("firstName is empty");
//			if(StringUtils.isEmpty(user.getLastName())) logger.info("lastName is empty");
//			if(StringUtils.isEmpty(user.getPassword())) logger.info("password is empty");
//			if(StringUtils.isEmpty(user.getEmail()) && StringUtils.isEmpty(user.getPhoneNo())) logger.info("both are empty");
			
			logger.error("Invalid request");
			response =new Response<Integer>(2002,"Request body is not valid",null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		User createdUser;
		try {
			createdUser = userServiceImpl.registerUser(user);
			
			if(createdUser == null) {
				logger.error("Couldn't register new user");
				response =new Response<Integer>(2003,"Request body is not valid",null);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
			logger.info("User successfully registered!");
			response =new Response<Integer>(2000,"Customer successfully registered",null);
			response.setData(createdUser.getId());
			
			return ResponseEntity.status(HttpStatus.CREATED).body(createdUser.getId());
			} catch (Exception e) {
				logger.catching(e);
				response =new Response<>(2003,e.getMessage(),null);
				return ResponseEntity.badRequest().body(null);
			}
		}
	
	@PostMapping("/login")
	public ResponseEntity<Integer> loginUser(@RequestBody User user, BindingResult errors){
		
		logger.info("Inside loginUser()");
		
		if(errors.hasErrors()) {			
			logger.error("Invalid request!..caught by Binding Result");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		String email = user.getEmail();
		
		if(StringUtils.isEmpty(email)) {
			logger.error("Email is empty");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		logger.info("Fetching user by emailId....");
		User user1 = userRepository.findByEmail(email);
		
		if(user1 == null) {
			logger.error("User with this id not found");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		logger.info(user1.getPassword());
		String pass = user1.getPassword();
		
		if(user.getPassword().equals(pass)) { 
			logger.info("Password matched");
			return ResponseEntity.ok().body(user1.getId());
		}
		else {
			logger.info("Password doesn't match");
			return ResponseEntity.ok().body(null);
		}
		
	}
	
	@PostMapping("/getInfo")
	public ResponseEntity<User> getUserInfo(@RequestBody User user, BindingResult errors){
		
		logger.info("inside getUserInfo()");
		
		if(errors.hasErrors()) {			
			logger.error("Invalid request!..caught by Binding Result");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		if(user == null) {
			logger.error("Id not provided!");
			return ResponseEntity.badRequest().body(null);
		}
		
		if(user.getId() == null) {
			logger.error("Id not found!");
			return ResponseEntity.badRequest().body(null);
		}
		
		User user1 = userRepository.findById(user.getId()).orElse(null);
		
		if(user1 == null) {
			logger.error("User not found!");
			return ResponseEntity.badRequest().body(null);
		}
		logger.info("User found!"); 
		return ResponseEntity.ok().body(user1);
	}
	
	@PostMapping("/addNews")
	public ResponseEntity<News> addNews( @RequestBody News newNews, BindingResult errors){
		
		if(errors.hasErrors()) {
			logger.error("Invalid Request");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		if(newNews == null) {
			logger.error("news not provided");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} 
		
		if(StringUtils.isEmpty(newNews.getHeadLine()) || StringUtils.isEmpty(newNews.getNewsData()) || newNews.getUser() == null) {
			logger.error("News data insufficient");
			return ResponseEntity.badRequest().body(null);
		}
		
		LocalDateTime localDateTime = LocalDateTime.now();
		//Date date = Date.from( localDateTime.atZone( ZoneId.).toInstant());
		 
		//newNews.setCreatedDate(new Date(System.currentTimeMillis()));
		newNews.setCreatedDate(localDateTime);
		newNews.setIsActive(true);
		newNews.setNoOfViews(0);
		newNews.setNoOfViews(0);
		newNews.setNumComments(0);
		Integer userId = newNews.getUser().getId();
		User user = userRepository.findById(userId).orElse(null);
		
		if(user == null) {
			logger.error("User not found");
			return ResponseEntity.badRequest().body(null);
		}
		
		newNews.setUser(user);
		logger.info("inserting");
		News dbNews = newsRepository.save(newNews);
		//userRepository.saveAndFlush(user);
		
		logger.info("Inserted");
		
		return ResponseEntity.ok().body(dbNews);
	}
/*	
	@PostMapping("/news/addPicture")
	public void savePicture(@RequestParam("file") MultipartFile file) throws Exception{
		
		File convFile = new File("src/main/resources/static/upload/"+file.getOriginalFilename());
		
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		Blob blob = new SerialBlob(file.getBytes());
		
		fos.write(file.getBytes());
		fos.close();
		logger.info(convFile.getName());
		
		newsRepository.save(newNews)
		
	}
  */ 
	
	@PostMapping("/getAllNews")
	public ResponseEntity<List<News>> getAllNews(){
		
		return ResponseEntity.ok().body(newsRepository.findAllByOrderByCreatedDateDesc());
	}
	
	@PostMapping("/addComment")
	public ResponseEntity<Comment> addComment(@RequestBody String passReq){
		
		try {
			
			UserRequestModel passRequest = JsonUtill.convertJsonToJava(passReq, UserRequestModel.class);
			
			User user = userRepository.findById(passRequest.getUserId()).orElse(null);
			
			News news = newsRepository.findById(passRequest.getNewsId()).orElse(null);
			
			String data = passRequest.getCommentData();
			
			Comment comment = new Comment();
			comment.setCommentData(data);
			
			comment.setUser(user);
			comment.setNews(news);
			commentRepository.save(comment);
			
			news.setNumComments(news.getNumComments()+1);
			newsRepository.save(news);
			
			return ResponseEntity.ok().body(comment);
		}
		catch(Exception e) {
			logger.catching(e);
		}
		
		return null;
	}
	
	@PostMapping("/getComments")
	public ResponseEntity<List<Comment>> getComments(@RequestBody String passReq){
		
		try {
			
			UserRequestModel passRequest = JsonUtill.convertJsonToJava(passReq, UserRequestModel.class);
			
			News news = newsRepository.findById(passRequest.getNewsId()).orElse(null);
			
			List<Comment> comments = commentRepository.findByNews(news);
			return ResponseEntity.ok().body(comments);
		}
		catch(Exception e) {
			logger.catching(e);
		}
		
		return null;
	}
	
	@PostMapping("/getNewsInfo")
	public ResponseEntity<News> getNewsInfo(@RequestBody String passReq){
		
		try {
			
			UserRequestModel passRequest = JsonUtill.convertJsonToJava(passReq, UserRequestModel.class);
			logger.info("Before");
			News news = newsRepository.findById(passRequest.getNewsId()).orElse(null);
			news.setNoOfViews(news.getNoOfViews()+1);
			newsRepository.save(news);
			
			
			logger.info(news);
			return ResponseEntity.ok().body(news);
		}
		catch(Exception e) {
			logger.catching(e);
		}
		
		return null;
	}
	@PostMapping("/hasVoted")
	public ResponseEntity<Map<String, String>> hasVoted(@RequestBody String passReq){
		
		try {
			
			UserRequestModel passRequest = JsonUtill.convertJsonToJava(passReq, UserRequestModel.class);
			logger.info("Before");
			
			User user = userRepository.findById(passRequest.getUserId()).orElse(null);
		    News news = newsRepository.findById(passRequest.getNewsId()).orElse(null);
			
			Vote vote = voteRepository.findByUserAndNews(user, news);
			
			HashMap<String, String> map = new HashMap<>();
			
			if(vote == null) {
				map.put("hasVoted", "No");
			}
			else {
				map.put("hasVoted", "Yes");
				map.put("votedFor", vote.getIsUp().toString());
			}
			
			logger.info(news);
			return ResponseEntity.ok().body(map);
		}
		catch(Exception e) {
			logger.catching(e);
		}
		
		return ResponseEntity.ok().body(null);
	}
	
	
	@PostMapping("/voteIt")
	public ResponseEntity<Map<String, String>> voteIt(@RequestBody String passReq){
		

		HashMap<String, String> map = new HashMap<>();
		try {
			
			UserRequestModel passRequest = JsonUtill.convertJsonToJava(passReq, UserRequestModel.class);
			//logger.info("Before");
			//logger.info(""+passRequest.getUserId());
			News news = newsRepository.findById(passRequest.getNewsId()).orElse(null);
			//logger.info("News found!");
			User user = userRepository.findById(passRequest.getUserId()).orElse(null);
			//logger.info("User found!");
			Integer value = passRequest.getUpVote();
			Vote vote = new Vote();
			LocalDateTime localDateTime = LocalDateTime.now();
			
			vote.setCreatedDate(localDateTime);
			logger.info("before credibility");
			vote.setCredibilityScore(user.getCredabilityScore());
			logger.info("after credibility");
			if(value == 0)
				vote.setIsUp(false);
			else if(value == 1)
				vote.setIsUp(true);
			else 
				throw new Exception("Wrong input");
			vote.setUser(user);
			vote.setNews(news);
			logger.info("saving in db");
			voteRepository.save(vote);
			logger.info("saved vote");
			news.setNumVotes(news.getNumVotes()+1);
			newsRepository.save(news);
		    map.put("result", "success");
			return ResponseEntity.ok().body(map);
		}
		catch(Exception e) {
			logger.catching(e);
			
		}

	    map.put("result", "failure");
		return ResponseEntity.ok().body(map);
	}
	

	@PostMapping("/getFinalScore")
	public ResponseEntity<Map<String, String>> getFinalScore(@RequestBody String passReq){
		
		try {
			
			UserRequestModel passRequest = JsonUtill.convertJsonToJava(passReq, UserRequestModel.class);
			logger.info("Before");
			
			News news = newsRepository.findById(passRequest.getNewsId()).orElse(null);
			logger.info(news);
			
			List<Vote> upVotes =  voteRepository.findByNewsAndIsUp(news, true);
			List<Vote> downVotes =  voteRepository.findByNewsAndIsUp(news, false);
			
			Float totalUp = (float)0, totalDown = (float)0;
			
			for(Vote v: upVotes) {
				totalUp+=v.getCredibilityScore();
			}
			
			for(Vote v: downVotes) {
				totalDown+=v.getCredibilityScore();
			}
			
			HashMap<String, String> map =new HashMap<>();
			map.put("totalUpScore", totalUp.toString());
			map.put("totalDownScore", totalDown.toString());
			
			return ResponseEntity.ok().body(map);
		}
		catch(Exception e) {
			logger.catching(e);
		}
		
		return ResponseEntity.ok().body(null);
	}
	
	@PostMapping("/getTrendingNews")
	public ResponseEntity<List<News>> getTrendingNews(){
		
		try {
			
			List<News> newsList = newsRepository.findByIsActiveOrderByNumVotesDesc(true);
			
			return ResponseEntity.ok().body(newsList);
		}
		catch(Exception e) {
			logger.catching(e);
		}
		
		return null;
	}
	
	
	
}
