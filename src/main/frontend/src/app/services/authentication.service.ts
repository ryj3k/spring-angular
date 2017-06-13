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
        return this.http.post('api/auth/login',
               JSON.stringify({ username: username, password: password }))
        .map((response: Response) => {
                const user = response.json();
                if (user && user.token) {
                    localStorage.setItem('currentUser', JSON.stringify(user));
                }
            });

    }

    logout() {
        localStorage.removeItem('currentUser');
    }


}
