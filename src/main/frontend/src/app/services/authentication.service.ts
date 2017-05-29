import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';
import {AuthResponse} from '../models/authResponse';
@Injectable()
export class AuthenticationService {
    private result: Boolean;
    constructor(private http: Http) {}

    login(username: string, password: string) {
       console.log('Start post');
        return this.http.post('api/auth/login',
               JSON.stringify({ username: username, password: password }))
        .map((response: Response) => {
                // login successful if there's a jwt token in the response
                let user = response.json();
                if (user && user.token) {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify(user));
                }
            });      

    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
    }


}
