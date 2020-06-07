import { Component, OnInit, HostListener } from '@angular/core';

import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Product } from 'src/app/product';
import { ProductService } from 'src/app/product.service';
import { CartItem } from 'src/app/cartItem';
import { CartService } from 'src/app/cart.service';
import { ShoppingcartService } from 'src/app/shoppingcart.service';


@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  public products: Product;
  public productData: Product;
  public carts: CartItem[] = [];
  userId: number;
  counter: number = 0;

  constructor(
    private productService: ProductService, 
    private shoppingCartService: ShoppingcartService,
    private router: Router) { }

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(){
    this.productService.getProducts().subscribe(data=>{
      this.products = data;
      console.log("all products>>" + this.products);
    }, error => console.log(error));
  }

  findProduct(id:number){
    this.productService.getProductById(id).subscribe(data=>{
      this.productData = data;
      console.log("product is>>" + this.productData);
    }, error => console.log(error));
  }

  addItemToCart(product:Product){
    var userId = JSON.parse(localStorage.getItem("userId"));
    var cartItem: CartItem = {
      product: product,
      quantity: 1,
      id: null,
      userId: userId,
      status: "OPENED"
    };
    this.shoppingCartService.addCartItem(userId, cartItem).subscribe(data=>{
      this.carts = data;
      this.counter++;
      console.log("carts is>>" + this.carts);
    }, error => console.log(error));
  }

  // addToCart(product:Product){
  //   // this.cartService.addCartItem(id).subscribe(data=>{
  //   //   console.log("data is>>" + data);
  //   //   this.carts.push(data);
  //   //   localStorage.setItem("cartItem", data);
  //   //   console.log("cart after addition is>>" + JSON.stringify(data));
  //   // }, error => console.log(error));
    
  //   // console.log("cart after addition is are >>" + JSON.stringify(localStorage.getItem("cart")));
  //   // console.log("cart after addition is>>" + JSON.stringify(this.carts));
 
  //   // this.productService.getProductById(id).subscribe(data=>{
  //   // //  console.log("get product by id in add to cart"+ data);
  //   //   localStorage.setItem("product", JSON.stringify(data));
  //   // }, error => console.log(error));
  //   // console.log("product after addition is>>" + JSON.stringify(localStorage.getItem("product")));
  //   this.productService.getProductById(product.id).subscribe(data=>{
  //     sessionStorage.setItem('product', JSON.stringify(data));
  //   }, error => console.log(error));
  //   this.productService.product = product;
  //   this.router.navigate(['addToCart']);
  // }

  // addCartItem(product:Product){
  //     console.log("cart in storage>>>" + sessionStorage.getItem('cart'));
  //       var cartItem: CartItem = {
  //         product: product,
  //         quantity: 1,
  //         id: null,
  //         userId: JSON.parse(localStorage.getItem("userId")),
  //         status: "open"
  //       };
  //     if (sessionStorage.getItem('cart') == null) {
  //       let cart: any = [];
  //       cart.push(cartItem);
  //       sessionStorage.setItem('cart', JSON.stringify(cart));
  //       console.log("Add first cart item>>>" + sessionStorage.getItem('cart'));
  //     } else {
  //       console.log("cart existing items>>>" + sessionStorage.getItem('cart'));
  //       let cart: any = JSON.parse(sessionStorage.getItem('cart'));
  //       let index: number = -1;
  //       for (var i = 0; i < cart.length; i++) {
  //         let item: any = cart[i];
  //         console.log("item>>>" + cart[i]);
  //         if (item.product.id == product.id) {
  //           index = i;
  //           break;
  //         }
  //      }
  //       if (index == -1) {
  //         cart.push(cartItem);
  //       //  sessionStorage.setItem('cart', JSON.stringify(cart));
  //       } else {
  //         cart[index].quantity = cart[index].quantity+=1;
  //       //  sessionStorage.setItem("cart", JSON.stringify(cart));
  //       }
  //       sessionStorage.setItem("cart", JSON.stringify(cart));
  //     }
  //     this.counter++;
  //     console.log("cart data>>>" + sessionStorage.getItem('cart'));    
  // }
  
  viewCart(){
    this.router.navigate(['viewCart']);
  }

}
