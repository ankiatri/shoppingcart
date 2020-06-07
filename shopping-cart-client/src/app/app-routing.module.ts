import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductsComponent } from './shoppingcart/products/products.component';
import { ViewProductComponent } from './shoppingcart/view-product/view-product.component';
import { CartComponent } from './shoppingcart/cart/cart.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';



const routes: Routes = [
  { path: '',  redirectTo: 'register', pathMatch: 'full'},
  { path: 'home',  component: HomeComponent},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent},

  // otherwise redirect to home
 // { path: '**', redirectTo: '' },
 // { path: '', redirectTo: 'product', pathMatch: 'full' },
  { path: 'product', component: ProductsComponent },
  { path: 'viewProduct/:id', component: ViewProductComponent },
  { path: 'viewCart', component: CartComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
