
## RewardsApp

RewardsApp is Rest API based application using which the reward points are allocated to the customers based on their transactios.


### Code Walkthrough & Postman Transactions 
Ref: https://www.loom.com/share/1180675ede454ff2b906b6d104a11359?sid=f1db5042-2b37-4ef5-98b9-77260eff10c8

### Git Repo
[RewardsApp git repo
](https://github.com/battu1807/InfosysScreening.git)

Minimum requirements
For building and running the application you need:

JDK 21
Maven 3.9.9

## Tech Stack

**Backend:** Java, Spring boot, Rest API, H2 Database

**TestClient:** Postman




## Run Locally

Clone the project

```bash
  git clone https://github.com/battu1807/InfosysScreening.git
```

Go to the project directory

```bash
  cd rewardsapp
```

To run application

```bash
 Option 1 : Build jar and execute
 ~\rewardsapp>mvn package -Dmaven.test.skip
 ~\rewardsapp>java -jar target\rewardsapp-0.0.1-SNAPSHOT.jarl

  Option 2 : Build jar and execute
 ~\rewardsapp>mvn clean install -Dmaven.test.skip
 ~\rewardsapp>java -jar target\rewardsapp-0.0.1-SNAPSHOT.jar

 Option 3
 D:\TestApp\rewardsapp>mvn spring-boot:run

```

## API Reference
Api-doc Ref: 
https://raw.githubusercontent.com/battu1807/InfosysScreening/refs/heads/main/api-docs.json
https://github.com/battu1807/InfosysScreening/blob/main/api-docs.json

### Postman Collection:
https://github.com/battu1807/InfosysScreening/blob/main/rewardsapp.postman_collection.json

### Customer API
Customer Payload

{
  "customerId": 1,
  "customerName": "customerOne",
  "emailId": "customerOne@gmail.com",
  "phoneNumber": "1234123411",
  "password": "customerOne@123"
}

#### Register Customer
```http
  POST /customer/register
```

#### Customer Login
```http
  GET /customer/login
```
#### Customer Logout

```http
  GET /customer/logout
```
### Order / Transaction API
Order Payload

{
    "transactionId": 1,
    "spentDetails": "books",
    "amount": 230,
    "date": "2022-01-13",
    "customerId": 1
}

#### Add Order /Transaction
```http
  POST /order
```

#### Get Order / Transaction
```http
  GET /order/{transactionId}
```
#### Update Order / Transaction

```http
  PUT /order
```

#### Delete Order / Transaction

```http
  DELETE /order/{transactionId}
```

#### Post Multiple Orders / Transactions

```http
  POST /order/postMultiple
```


### Reward API
Reward Payload

{
    "rewardId": 1,
    "customerId": 1,
    "rewardMonth": 1,
    "rewardYear": 2024,
    "points": 10
}

#### Get all rewards
```http
  GET /reward/all
```

#### Get rewards for specific customer
```http
  GET /reward/customer/{customerId}
```
#### Get total rewards for every customer, total & monthly points

```http
  GET /rewards/total
```

#### Swagger UI
http://localhost:8080/swagger-ui/index.html

