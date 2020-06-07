import { Component, HostListener } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Shopping Cart Application';

  constructor(private router: Router) {    
  }

  logout(){
    localStorage.removeItem("userId");
    this.router.navigate(['login']);
  }

}
