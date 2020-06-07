import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { Subscription } from 'rxjs';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

    userId: number;
    currentUser: User ;
    users: User[] = [];

    constructor(private userService: UserService,
      private router: Router) {
           
    }

    ngOnInit() {
       this.userId = JSON.parse(localStorage.getItem("userId")); 
       if(this.userId!=null) {
       this.getUserById(this.userId);
       } else {
        this.router.navigate(['login']);
       }
    }

    ngOnDestroy() {
    }

    private getUserById(id:number) {
      this.userService.getUserById(id).subscribe(user => {
         console.log("user is>>" + user);
          this.currentUser = user;
      });
  }
  
}
