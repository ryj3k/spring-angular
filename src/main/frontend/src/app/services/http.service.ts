import { Injectable, Injector } from '@angular/core';
import { Request, XHRBackend, RequestOptions, Response, Http, RequestOptionsArgs, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';
import { AuthResponse } from '../models/index';
// import { AuthService } from './auth.service';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
@Injectable()
export class ExtendedHttpService extends Http {
  private router;
  private authService;
//  private failResponse : FailResponse;
  constructor(  backend: XHRBackend, defaultOptions: RequestOptions, private injector: Injector) {
    super(backend, defaultOptions);
  }


  request(url: string | Request, options?: RequestOptionsArgs): Observable<Response> {

    if (typeof url === 'string') {
      if (!options) {
        options = { headers: new Headers() };
      }
      this.jwtHeaders(options);
    } else {
      this.jwtHeaders(url);
    }
    return super.request(url, options).catch(this.catchErrors());
  }

private jwtHeaders(objectToSetHeadersTo: Request | RequestOptionsArgs) {
      const currentUser = JSON.parse(localStorage.getItem('currentUser'));
      if (currentUser && currentUser.token) {
         objectToSetHeadersTo.headers.set('X-Authorization', 'Bearer ' + currentUser.token );
      }
  }


  private catchErrors() {
      return (res: Response) => {
          if (this.router == null) {
              this.router = this.injector.get(Router);
          }
          if (res.status === 401 || res.status === 403) {
              localStorage.removeItem('currentUser');
              const failResponse = new  AuthResponse().fromJSON(res.json());
              this.router.navigate(['/login', res.status]);
          }
          return Observable.throw(res);
      };
    }

}
