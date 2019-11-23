import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import {CookieService} from 'ngx-cookie-service';
@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private router: Router, private cookieService:CookieService) {}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> | Promise<boolean> | boolean {
    const loggedIn = this.cookieService.get('login');
    if (loggedIn=="true") {
      return true;
    } else {
      this.router.navigate(['/']);
      return false;
    }
  }
}
