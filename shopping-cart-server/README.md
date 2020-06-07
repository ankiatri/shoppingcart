# Shopping Cart Solution
Shopping Cart Solution using Spring Boot and Angular 9

Solution is composed of three projects: 
  * shopping-cart-server: Spring Boot 2.3.0
  * shopping-cart-client: Angular 9
  
# Installation Instructions
 
 1) Clone the repository:
  git clone https://github.com/
  
 2) Build shopping-cart-server
  * cd ../shopping-cart-server
  * mvn clean install
  * mvn spring-boot:run
  
3) Build shopping-cart-client
  * cd ../shopping-cart-client
  * npm install
  * ng serve

# Shopping cart project to expose REST APIs for User, CartItem, Product
* REST APIs are implemented using `Spring Boot, JPA, Maven, H2 database`. 
* The  APIs are secured by spring basic authentication. 

## How to Run
 `shopping-cart-server`
* Install the project by running `mvn clean install` inside shopping-cart-server folder
* Once successfully built, run the REST APIs by using any rest-client (e.g. postman) with the Endpoints mentioned below
 `shopping-cart-client`
 * Install the project by running `npm install` inside shopping-cart-client folder
 * Once successfully built, run the UI by running `ng serve`

### Basic Authentication Credentials
Basic authentication is added for rest api's. To access complete functionality use below credentials:
```
username: username
password: password
```

## REST APIs Endpoints 
 They are seperately provided in postman collection

### Steps for UI execution

 * First register with user details
 * If already registered then directly login with username and password
 * Product list is showing all the list of products and price
 * Add products to cart
 * Go to cart to check the cart items
 * You can update the quantity, delete cart item and view product
 * Total price will show the calculated amount as per quantity and price

### To view H2 database
```
http://localhost:9090/shoppingCart/h2-console

JDBC URL : jdbc:h2:mem:shopping_cart_db
username: root
password:
```