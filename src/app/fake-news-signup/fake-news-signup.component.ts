import { Component, OnInit,ViewChild } from '@angular/core';
import {PostService} from '../services/post.service';
import {CookieService} from 'ngx-cookie-service';
@Component({
  selector: 'app-fake-news-signup',
  templateUrl: './fake-news-signup.component.html',
  styleUrls: ['./fake-news-signup.component.css']
})
export class FakeNewsSignupComponent implements OnInit {
  @ViewChild('firstname',{static:false}) firstName;
  @ViewChild('lastName',{static:false}) lastName;
  @ViewChild('email',{static:false}) email;
  @ViewChild('phone',{static:false}) phoneNo;
  @ViewChild('password',{static:false}) password;
  @ViewChild('emailLogin',{static:false}) emailLogin;
  @ViewChild('passwordLogin',{static:false}) passwordLogin;
  signUpButton=document.getElementById('signUp');
  signInButton = document.getElementById('signIn');
  message="";
  //container =document.getElementById('container');  
  container=document.getElementsByClassName('container') as HTMLCollectionOf<HTMLElement>;
  constructor(private postService:PostService,private cookieService:CookieService) { }

  ngOnInit() {
    //this.RegisterUser();
  }
   
  RegisterUser(){
    let firstName=this.firstName.nativeElement.value;
    let lastName=this.lastName.nativeElement.value;
    let email=this.email.nativeElement.value;
    let phone=this.phoneNo.nativeElement.value;
    let password=this.password.nativeElement.value;
    //console.log(firstName,lastName,password,email,phone);
    this.postService.RegisterUser(firstName,lastName,password,email,phone).subscribe(res=>{
      console.log(res);
      if(res){
        this.message="Your Account is created Successfully";
        location.replace("/browse");
        this.cookieService.set('userId',res);
      }
    })
  }
  
  Login(){
    let emailLogin=this.emailLogin.nativeElement.value;
    let passwordLogin=this.passwordLogin.nativeElement.value;
    console.log(emailLogin+" "+passwordLogin);
    this.postService.LoginUser(emailLogin,passwordLogin).subscribe(res=>{
      console.log(res);
      this.cookieService.set('userId',res);
      location.replace("/");  
    })
  } 

  Signup(){
    this.container[0].classList.add('right-panel-active');
  }
  Signin(){
    this.container[0].classList.remove('right-panel-active');
  }

}
