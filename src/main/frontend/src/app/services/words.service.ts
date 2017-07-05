import { Word } from './../models/word';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { Injectable } from '@angular/core';

@Injectable()
export class WordsService {

  constructor(private http: Http) {   }

  getAll() {
    return this.http.get('api/word/all').map((response: Response) => response.json());
  }

  saveOrUpdate(word: Word) {
    return this.http.put('api/word/save', word).map((response: Response) => response.json());
  }


}
