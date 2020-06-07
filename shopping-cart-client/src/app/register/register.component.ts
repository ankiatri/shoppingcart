import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../user.service';
import { User } from '../user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  loading = false;
  submitted = false;
  user:User;

  constructor(
      private formBuilder: FormBuilder,
      private router: Router,
      private userService: UserService
  ) { 
    console.log("entered in register page");
      // redirect to home if already logged in
     if (localStorage.getItem("userId")==null) { 
       this.router.navigate(['/']);
     }
  }

  ngOnInit() {
      this.registerForm = this.formBuilder.group({
          username: ['', Validators.required],
          password: ['', Validators.required],
          firstName: [''],
          lastName: [''],
          email: ['']
      });
  }

  // convenience getter for easy access to form fields
  get f() { return this.registerForm.controls; }

  onSubmit() {
      this.submitted = true;
      // stop here if form is invalid
       if (this.registerForm.invalid) {
           return;
       }
      console.log("in submit method of register page");
      this.loading = true;
      this.userService.saveUser(this.registerForm.value).subscribe(data=>{ 
        this.user = data
        localStorage.setItem("userId", JSON.stringify(this.user.id));
        console.log("create user and userid is" + this.user.id);
        this.router.navigate(['home']);
      }); 
      console.log("after service");
  }
  
}