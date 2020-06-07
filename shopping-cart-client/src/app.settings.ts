import { HttpHeaders } from '@angular/common/http';

export class AppSettings {
   public static get REST_ENDPOINT(): string {
     return 'http://localhost:9090/shoppingCart';
   } 

}