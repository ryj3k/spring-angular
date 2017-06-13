import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { Injectable } from '@angular/core';

@Injectable()
export class WordsService {

  constructor(private http: Http) {   }

  getAll() {
    return this.http.get('api/word/all').map((response: Response) => response.json());
  }

}
