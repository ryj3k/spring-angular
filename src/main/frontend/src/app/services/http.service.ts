import { Injectable, Injector } from '@angular/core';
import { Request, XHRBackend, RequestOptions, Response, Http, RequestOptionsArgs, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';
import { AuthResponse } from '../models/index';
//import { AuthService } from './auth.service';
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
      this.setHeaders(options);
    } else {
      this.setHeaders(url);
    }
    //console.log("urllll: " + JSON.stringify(url) +", Options:" + options);

    return super.request(url, options).catch(this.catchErrors());
  }
  private setHeaders(objectToSetHeadersTo: Request | RequestOptionsArgs) {

    //  if (this.authService == null) {
    //        this.authService = this.injector.get(AuthService);
    //  }
    //add whatever header that you need to every request
    //in this example I could set the header token by using authService that I've created
     //objectToSetHeadersTo.headers.set('token', this.authService.getToken());
  }

  private jwt() {
      // create authorization header with jwt token
      let currentUser = JSON.parse(localStorage.getItem('currentUser'));
      if (currentUser && currentUser.token) {
          let requestOptions = new RequestOptions();
          let headers = new Headers({ 'Authorization': 'Bearer ' + currentUser.token });
          requestOptions.headers = new Headers({ 'X-Authorization': 'Bearer ' + currentUser.token });
          return requestOptions;
      }
  }
  private catchErrors() {
      return (res: Response) => {
          if (this.router == null) {
              this.router = this.injector.get(Router);
          }
          if (res.status === 401 || res.status === 403) {
              localStorage.removeItem('currentUser');
              let failResponse :AuthResponse;
              failResponse = new  AuthResponse().fromJSON(res.json());
              console.log("errorCode", failResponse.errorCode);
              console.log("Body", res.json());
              console.log("Error_Token_Expired: redirecting to login.");


              this.router.navigate(['/login']);
          }
          return Observable.throw(res);
      };
    }

}
