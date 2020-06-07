import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../user.service';
import { User } from '../user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  user:User;

  constructor(
      private formBuilder: FormBuilder,
      private route: ActivatedRoute,
      private router: Router,
      private userService:UserService
  ) {
      // redirect to home if already logged in
      if (localStorage.getItem("userId")!=null) { 
          this.router.navigate(['home']);
      }
  }

  ngOnInit() {
    console.log("inside login page");
      this.loginForm = this.formBuilder.group({
          username: ['', Validators.required],
          password: ['', Validators.required]
      });
  }

  // convenience getter for easy access to form fields
  get f() { return this.loginForm.controls; }

  onSubmit() {
      this.submitted = true;
      // stop here if form is invalid
      if (this.loginForm.invalid) {
          return;
      }
      this.loading = true;
      this.userService.getUserByUsername(this.f.username.value).subscribe(data=>{ 
        if(data!=null){
          this.user = data
          localStorage.setItem("userId", JSON.stringify(this.user.id));
          this.router.navigate(['home']);
        }
      }, error => {
          console.log(error);
          this.loading = false;
      });
  }
  
}
