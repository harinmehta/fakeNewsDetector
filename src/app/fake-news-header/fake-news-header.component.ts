import { Component, OnInit } from '@angular/core';
import {CookieService} from 'ngx-cookie-service';
import {PostService} from '../services/post.service';
@Component({
  selector: 'app-fake-news-header',
  templateUrl: './fake-news-header.component.html',
  styleUrls: ['./fake-news-header.component.css']
})
export class FakeNewsHeaderComponent implements OnInit {
 profileName:boolean=false;
 user:boolean=false;
 firstname;
 lastname;
 userid;
  constructor(private cookieService:CookieService,private postService:PostService) { }

  ngOnInit() {
    this.checkUser();
    this.GetUserInfo();
  }
  GetUserInfo(){
    let value=this.cookieService.get('userId');
    this.postService.getUserInfo(value).subscribe(res=>{
      console.log(res);
      this.firstname=res.firstName;
      this.lastname=res.lastName;
      this.userid=res.id;
    })
  }
  checkUser(){
    let value=this.cookieService.get('userId');
    console.log(value);
    if(value){
      this.user=true;
    }
    else if(!value){
      this.user=false;
    }
    else{
      this.user=false;
    }
  }

  // getUserInfo(){
  //   let value=this.cookieService.get('userId');
  //   if(value){
  //     this.postService.getUserInfo(value).subscribe(res=>{
  //       console.log(res);
  //     })
  //   }
  // }


  logout(){
    this.cookieService.delete('userId');
    location.replace("/");
    this.profileName=false;
  }
}
