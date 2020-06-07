import { Injectable } from '@angular/core';
import { AppSettings } from 'src/app.settings';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CartItem } from './cartItem';

@Injectable({
  providedIn: 'root'
})
export class ShoppingcartService {
  private baseUrl =`${AppSettings.REST_ENDPOINT}/cart`;

  constructor(private http: HttpClient) { 
  }
  loadOpenCartItems(userId:number, status:string) : Observable<any> {
    return this.http.get(`${this.baseUrl}/${userId}/${status}`, headers);
  }
  removeCartItem(userId:number, cartItem: CartItem) : Observable<any> {
    return this.http.put(`${this.baseUrl}/remove/${userId}`, cartItem,  headers);
  }
  addCartItem(userId:number, cartItem: CartItem) : Observable<any> {
    return this.http.put(`${this.baseUrl}/add/${userId}`,cartItem, headers);
  }

  updateCartItem(userId:number, cartItem: CartItem) : Observable<any> {
    return this.http.put(`${this.baseUrl}/update/${userId}`,cartItem, headers);
  }
}
const headers = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'Basic ' + btoa('username:password')
  })
};
