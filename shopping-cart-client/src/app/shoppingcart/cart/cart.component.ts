import { Component, OnInit, Input } from '@angular/core';

import { Observable } from 'rxjs';

import { Router, ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/product';
import { ProductService } from 'src/app/product.service';
import { CartItem } from 'src/app/cartItem';
import { CartService } from 'src/app/cart.service';
import { ShoppingcartService } from 'src/app/shoppingcart.service';


@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cartItems: CartItem[] = [];
  total:number =0;
  userId: number;
  constructor(
    private shoppingCartService: ShoppingcartService,
    private router: Router) { }

  ngOnInit(): void {
    this.userId = JSON.parse(localStorage.getItem("userId"));
    if(this.userId!=null) {
      this.loadCartItems();
    } else {
      this.router.navigate(['login']);
    }
  }

  getTotal(){
    var total = 0;
    for (var i = 0; i < this.cartItems.length; i++) {
      total += this.cartItems[i].product.price * this.cartItems[i].quantity;
    }
    this.total = total;
  }

  loadCartItems(){
      this.shoppingCartService.loadOpenCartItems(this.userId, "opened").subscribe(data=>{
        this.cartItems = data;
        this.getTotal();
        console.log("all cartItems: " + data);
      }, 
      error => console.log(error));
  }

  removeCartItem(cartItem:CartItem){
    this.shoppingCartService.removeCartItem(this.userId, cartItem).subscribe(data=>{
      this.cartItems = data;
      console.log("cart item removed:" +this.cartItems);
    }, error => console.log(error));
    this.getTotal();
    window.location.reload();
  }

  updateCartItem(cartItem:CartItem, quantity:number){
    cartItem.quantity = quantity;
    cartItem.status = "OPENED";
      this.shoppingCartService.updateCartItem(this.userId, cartItem).subscribe(data=>{
        this.cartItems = data;
        console.log("cart updated:" +this.cartItems);
      }, error => console.log(error));
      this.getTotal();
  }

  
  submitOrder(){
    // this.shoppingCartService.loadOpenCartItems(this.userId, "opened").subscribe(data=>{
    //   var cartItems = data;
    //   for (var i = 0; i < cartItems.length; i++) {
    //     cartItems[i].status =  "SUBMITTED";
    //     console.log("inside submit order: "+ cartItems[i]);
    //     this.shoppingCartService.updateCartItem(this.userId, cartItems[i]).subscribe(data=>{
    //     //  this.cartItems = data;
    //       console.log("cart updated:" +data);
    //     }, error => console.log(error));
    //   }

    //   this.getTotal();
    //   console.log("all cartItems: " + data);
    // }, 
    // error => console.log(error));
    // window.location.reload();

    for (var i = 0; i < this.cartItems.length; i++) {
      this.cartItems[i].status =  "SUBMITTED";
      this.shoppingCartService.updateCartItem(this.userId, this.cartItems[i]).subscribe(data=>{
        this.cartItems = data;
        console.log("cart updated:" +this.cartItems);
      }, error => console.log(error));
    }
    this.getTotal();
    window.location.reload();
   
  }

  viewProduct(id:number){
    this.router.navigate(['viewProduct', id]);
  }

}
