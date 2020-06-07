import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppSettings } from 'src/app.settings';
import { Observable } from 'rxjs';
import { Product } from './product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  public product: Product;
  private baseUrl =`${AppSettings.REST_ENDPOINT}/product`;
  constructor(private http: HttpClient) { 

  }
  getProducts() : Observable<any> {
    return this.http.get(`${this.baseUrl}`, headers);
  }
  getProductById(id:number) : Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`, headers);
  }
}
const headers = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'Basic ' + btoa('username:password')
  })
};