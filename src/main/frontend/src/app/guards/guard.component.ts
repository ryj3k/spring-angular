import { JwtToken } from './../models/jwtToken';
import { Response } from '@angular/http';
import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { JwtHelper } from 'angular2-jwt';
@Injectable()
export class AuthGuard implements CanActivate {
    constructor(private router: Router) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

        if (localStorage.getItem('currentUser')) {
           const token: JwtToken  = JSON.parse(localStorage.getItem('currentUser'));
           const helper: JwtHelper = new JwtHelper();
           if (helper.isTokenExpired(token.token)) {
             this.router.navigate(['/login', 403], { queryParams: { returnUrl: state.url }});
             return false;
           }
           return true;
        }

        this.router.navigate(['/login', 200], { queryParams: { returnUrl: state.url }});
        return false;
    }

}
