import { Component } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'app',
    templateUrl: 'app.component.html'
})
export class AppComponent {
isCollapsed = false;

isLogged() {
  return localStorage.currentUser != null;
}
showMenu() {
  this.isCollapsed = !this.isCollapsed;
}



}
