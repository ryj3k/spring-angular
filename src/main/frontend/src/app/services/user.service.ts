import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';

import { User } from '../models/index';

@Injectable()
export class UserService {
    constructor(private http: Http) { }

    getAll() {
        return this.http.get('api/customer/all').map((response: Response) => response.json());
    }

    getById(id: number) {
        return this.http.get('/api/users/' + id).map((response: Response) => response.json());
    }

    create(user: User) {
        return this.http.post('/api/users', user).map((response: Response) => response.json());
    }

    update(user: User) {
        return this.http.put('/api/users/' + user.id, user).map((response: Response) => response.json());
    }

    delete() {
        return this.http.post('/api/word/save', {name: 'Imie', translations: ['Imie', 'Imie2'] }).map(
          (response: Response) => response.json());
    }

}
