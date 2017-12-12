import { GuardService } from './guards/guard.service';
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
import { ModalModule } from 'ngx-bootstrap';
import { DynamicInputListComponent } from './dynamic-input-list/dynamic-input-list.component';
import { DataTableComponent } from './data-table/data-table.component';

@NgModule({
  declarations: [
     AppComponent,
     AlertComponent,
     HomeComponent,
     LoginComponent,
     WordsComponent,
     DynamicInputListComponent,
     DataTableComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    // AlertModule.forRoot(),
    BrowserModule,
    FormsModule,
    HttpModule,
    routing,
    ModalModule.forRoot()
  ],
  providers: [
  {provide: Http, useClass: ExtendedHttpService },
   AuthGuard,
   AlertService,
   AuthenticationService,
   UserService,
   WordsService,
   GuardService
 ],
  bootstrap: [AppComponent]
})
export class AppModule { }
