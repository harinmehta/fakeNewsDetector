import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) {}
  News: {
     headLine: string,
     newsData: string,
     user:{
       id:Number
     }
  };
  RegisterUser(firstName,lastName,password,email,phoneNo):Observable<any>{
    return this.http.post('http://192.168.43.23:8080/user/register',{firstName,lastName,password,email,phoneNo});
  }

  LoginUser(email,password):Observable<any>{
    return this.http.post('http://192.168.43.23:8080/user/login',{email,password});
  }
  getUserInfo(id):Observable<any>{
    return this.http.post('http://192.168.43.23:8080/user/getInfo',{id});
  }

  AddNews(headline,newsData,id):Observable<any>{
    this.News={
      headLine:headline,
      newsData:newsData,
      user:{
        id:id
      }
    }
    return this.http.post('http://192.168.43.23:8080/user/addNews',this.News);
  }
  GetAllNews():Observable<any>{
     return this.http.post('http://192.168.43.23:8080//user/getAllNews',{});
  }
  GetSingleNews(newsId):Observable<any>{
    return this.http.post('http://192.168.43.23:8080//user/getNewsInfo',{newsId});
  }
  AddCommentOnNews(commentData,newsId,userId):Observable<any>{
    return this.http.post('http://192.168.43.23:8080/user/addComment',{commentData,newsId,userId});
  }
  GetCommentOnNews(newsId):Observable<any>{
    return this.http.post('http://192.168.43.23:8080/user/getComments',{newsId});
  }
  UpvoteDownvote(userId,newsId,upVote):Observable<any>{
    return this.http.post('http://192.168.43.23:8080/user/voteIt',{userId,newsId,upVote});
  }
  GetTrendingNews():Observable<any>{
    return this.http.post('http://192.168.43.23:8080/user/getTrendingNews',{});
  }
  UserHasVoted(userId,newsId):Observable<any>{
    return this.http.post('http://192.168.43.23:8080/user/hasVoted',{userId,newsId});
  }
  GetFinalScore(newsId):Observable<any>{
    return this.http.post('http://192.168.43.23:8080/user/getFinalScore',{newsId});
  }
}
