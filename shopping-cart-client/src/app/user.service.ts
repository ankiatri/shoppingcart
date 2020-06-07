import { Injectable } from '@angular/core';
import { AppSettings } from 'src/app.settings';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl =`${AppSettings.REST_ENDPOINT}/user`;

  constructor(private http: HttpClient) { 
  }
  getUsers() : Observable<any> {
    return this.http.get(`${this.baseUrl}`, headers);
  }
  getUserByUsername(username:string) : Observable<any> {
    return this.http.get(`${this.baseUrl}/username/${username}`, headers);
  }

  getUserById(id:number) : Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`, headers);
  }
  saveUser(user: User) : Observable<any> {
    return this.http.post(`${this.baseUrl}`, user,  headers);
  }

}
const headers = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'Basic ' + btoa('username:password')
  })
};
