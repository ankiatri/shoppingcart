import { Injectable } from '@angular/core';
import { AppSettings } from 'src/app.settings';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CartItem } from './cartItem';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private baseUrl =`${AppSettings.REST_ENDPOINT}/cart`;

  constructor(private http: HttpClient) { 
  }
  loadCartItems() : Observable<any> {
    return this.http.get(`${this.baseUrl}`, headers);
  }
  removeCartItem(id:number) : Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, headers);
  }
  addCartItem(id:number) : Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`, headers);
  }
}

const headers = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'Basic ' + btoa('username:password')
  })
};

