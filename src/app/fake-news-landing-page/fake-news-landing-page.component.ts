import { Component, OnInit ,ViewChild } from '@angular/core';
import {PostService} from '../services/post.service';
import {CookieService} from 'ngx-cookie-service';
@Component({
  selector: 'app-fake-news-landing-page',
  templateUrl: './fake-news-landing-page.component.html',
  styleUrls: ['./fake-news-landing-page.component.css']
})
export class FakeNewsLandingPageComponent implements OnInit {
    firstname;
    lastname;
    show:boolean=true;
    @ViewChild('email',{static:false}) email;
    @ViewChild('password',{static:false}) password;
  constructor(private postService:PostService,private cookieService:CookieService) { }
  ngOnInit() {
    this.GetUserInfo();
  }

  GetUserInfo(){
    let value=this.cookieService.get('userId');
    if(value){
      this.show=false;
      this.postService.getUserInfo(value).subscribe(res=>{
        console.log(res);
        this.firstname=res.firstName;
        this.lastname=res.lastname;
      })
    }
    // this.postService.getUserInfo(value).subscribe(res=>{
    //   console.log(res);
    //   this.firstname=res.firstName;
    //   this.lastname=res.lastname;
    // })
  }

  Login(){
    let email=this.email.nativeElement.value;
    let password=this.password.nativeElement.value;

    this.postService.LoginUser(email,password).subscribe(res=>{
      this.cookieService.set('userId',res);
      location.replace("/");
    })
  }

}
