import { Product } from './product';

export class CartItem{
    product:Product;
    quantity:number;
    id:number;
    userId:number;
    status:string;
}