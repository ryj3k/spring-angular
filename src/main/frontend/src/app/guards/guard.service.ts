import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { Injectable } from '@angular/core';

@Injectable()
export class GuardService {
  constructor( private http: Http) { }

   check() {
       return this.http.get('api/auth/check').map((response: Response) => response.json());

    }
}
