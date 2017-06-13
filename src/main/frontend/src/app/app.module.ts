// import { AlertModule } from 'ngx-bootstrap';
import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { Http, HttpModule } from '@angular/http';

// used to create fake backend


import { AppComponent }  from './app.component';
import { routing }        from './app.routing';

import { AlertComponent } from './directives/index';
import { AuthGuard } from './guards/index';
import { AlertService, AuthenticationService, UserService, ExtendedHttpService, WordsService } from './services/index';
import { HomeComponent } from './home/index';
import { LoginComponent } from './login/index';
import { WordsComponent } from './words/words/words.component';

@NgModule({
  declarations: [
     AppComponent,
     AlertComponent,
     HomeComponent,
     LoginComponent,
     WordsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    // AlertModule.forRoot(),
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  providers: [
  {provide: Http, useClass: ExtendedHttpService },
   AuthGuard,
   AlertService,
   AuthenticationService,
   UserService,
   WordsService
 ],
  bootstrap: [AppComponent]
})
export class AppModule { }
