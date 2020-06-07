import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ProductService } from 'src/app/product.service';
import { Product } from 'src/app/product';



@Component({
  selector: 'app-view-product',
  templateUrl: './view-product.component.html',
  styleUrls: ['./view-product.component.css']
})
export class ViewProductComponent implements OnInit {
  product: Product= new Product;
  id:number;
  constructor(private productService: ProductService,
    private route: ActivatedRoute, 
    private router:Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.getProduct(this.id);
  }

  getProduct(id:number){
    this.productService.getProductById(this.id).subscribe(data=>{
        this.product = data;
      }, error => console.log(error));
  }
  viewCart(){
     this.router.navigate(['viewCart']);
   }
}
