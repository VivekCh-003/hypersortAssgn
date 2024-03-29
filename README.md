# hypersortAssgn

To run this project you need to have MySQL workbench installed

MySQL username = root
MySQL password = root
MySQL port = 3306
MySQL schema name = hypersort

run this project on localhost port - 8080


API Endpoints:
1. Get inventory detials:
	Endpoint: GET localhost:8080/inventory

2. Get Coupons:
	Endpoint : GET localhost:8080/fetchCoupons

3. Post an order:
	Endpoint: POST localhost:8080/1/order?quantity=10&coupon=OFF5

4. Mock the payment api:
	Endpoint: POST localhost:8080/1/100/pay?amount=950

5. Get Orders of particular user:
	Endpoint: GET localhost:8080/1/orders

6. Get Orders of particular user with order id:
	Endpoint: GET localhost:8080/1/orders/100


To use the API endpoint you can use platforms like Postman


Make sure that there are few user ids and coupons in the database before testing these endpoints and while entering user data in the database no need to fill out the coupons column, inserting user id is fine
