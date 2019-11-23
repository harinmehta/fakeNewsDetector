import { Injectable } from '@angular/core';
import {CookieService} from 'ngx-cookie-service';
@Injectable()
export class AuthService {

  constructor(private cookieService:CookieService) { }
  loggedIn = "false";
  
    
    login() {
      this.loggedIn ="true";
      this.cookieService.set('login',this.loggedIn);
      console.log(this.loggedIn," login")
    } 
    logout() {
      this.cookieService.delete('login');
      this.cookieService.delete('userId');
      this.loggedIn = "false";
    }
}
