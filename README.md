# Money transfer API

This API runs in a docker container, provides functionality to manage
accounts and money transfers between them using a dockerised mysql database.

## Development
This API was developed in a Windows 10 environment, making use of the following tools:

**Initializer [Spring initializr](https://start.spring.io/) / to see the specific project configuration open "sprogInitializer.JPG"**
    - Maven project
        - Apache Maven (Installed version 3.9.2)
    - Programming language Java
        - Java JDK 17.0.7
    - Framework spring boot 3.0.6
        - MySQL driver
        - Spring Web
        - Spring Data JPA

**IntelliJ IDEA**
    - GitHub and IntelliJ integration to facilite uploading code directly to the github repository

**Docker**
    - version 23.0.5

## Prerequisites
    - Docker
    - Knowledge how to use docker commands

## Deploy in Linux
1. Clone the repository: `git clone https://github.com/wherrmat/moneyTransfer.git`
2. Go to the project directory: `cd moneyTransfer`
3. Give execution permissions to the init.sh file: `chmod +x init.sh`
4. Execute the init.sh file: `sudo ./init.sh`

## Test
The test script makes Http queries to the API to test the endpoints and some exceptions
1. Attempt to obtain the list of accounts (empty)
2. Account creation
3. Query the list of accounts
4. Query accounts individually by id and account number
5. Delete individual accounts by id and account number
6. Attempt to query non-existent accounts by id and account number
7. Attempt to obtain transfer list (empty)
8. Making of transfers
9. Attempt of transfer without sufficient funds
10. Attempt of transfer with non-existent source account
11. Attempt of transfer with non-existent destination account
12. Query the transfer list
13. Query the list of accounts with the new balances
14. Query a transfer by id
15. Query the list of transfers related to an account id

1. Give execution permissions to the test.sh file: `chmod +x test.sh`
2. Execute the init.sh file: `sudo ./test.sh`
   *If you want to repeat the test or remove the deployment from the project in docker (Optional)*
3. Give execution permissions to the delete.sh file: `chmod +x delete.sh`
4. Execute the delete.sh file: `sudo ./delete.sh`

# MySQL database
The database for this project is dockerised and can be accessed from outside the container
using the following connection details:
- **Host**: localhost
- **Port**: 3306
- **Username**: root
- **Password**: Malta_2023
- **Database name**: money_transfer_db

![Database diagram](/mysql_db/schema.png)

## Table "Accounts"
This table records accounts.

| Field              | Type          | Description                              |
|--------------------|---------------|------------------------------------------|
| id                 | int           | ID of the account, unique, (PRIMARY KEY) |
| transfer_timestamp | timestamp     | date and time of the transfer            |
| account_number     | varchar(20)   | Personalized account number, unique      |
| balance            | decimal(10,2) | Current account balance                  |

## Table "Transfers"
This table records transfers of funds between accounts.

| Field                      | Type          | Description                                  |
|----------------------------|---------------|----------------------------------------------|
| id                         | big int       | ID of the transfer, unique, (PRIMARY KEY)    |
| source_account_number      | int           | Id of the source account, (FOREIGN KEY)      |
| destination_account_number | int           | Id of the destination account, (FOREIGN KEY) | 
| amount                     | decimal(10,2) | Amount of the transfer                       |


# Use of the API
This API receives requests on local host on port 8080.

## Accounts

### Endpoint 1: Create an account
**Url**
`http://localhost:8080/accounts/create`

**Parameters**

| PARAMETER      | TYPE    | DESCRIPTION                    |
|----------------|---------|--------------------------------|
| balance        | decimal | Initial balance of the account |
| account_number | string  | Personalized account number    |

**Example**
`POST http://localhost:8080/accounts/create
Content-Type: application/json
{
    "balance": 1000.00,
    "account_number": "A001"
}`

**Response**
`HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 26 May 2023 13:55:16 GMT
Connection: close
{
"id": 1,
"balance": 1000.00,
"account_number": "A001"
}`

### Endpoint 2: Return a list with all accounts
**Url**
`http://localhost:8080/accounts/allaccounts`

**Parameters**
*No additional parameters required*

**Example**
`GET http://localhost:8080/accounts/allaccounts`

**Response**
`HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 26 May 2023 14:03:03 GMT
Connection: close
[
{
"id": 1,
"balance": 1000.00,
"account_number": "A001"
},
{
"id": 2,
"balance": 1000.00,
"account_number": "A002"
}
]`

### Endpoint 3: Return an account searched by ID
**Url**
`http://localhost:8080/accounts/getbyid/{id}`

**Parameters**

| PARAMETER | TYPE | DESCRIPTION                    |
|-----------|------|--------------------------------|
| id        | int  | Account id you want to consult |

**Example**
`GET http://localhost:8080/accounts/getbyid/1`

**Response**
`HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 26 May 2023 14:09:25 GMT
Connection: close
{
"id": 1,
"balance": 1000.00,
"account_number": "A001"
}`

### Endpoint 4: Return an account searched by account number
**Url**
`http://localhost:8080/accounts/getbyaccountnumber/{account_number}`

**Parameters**

| PARAMETER       | TYPE   | DESCRIPTION                        |
|-----------------|--------|------------------------------------|
| account_number  | string | Account number you want to consult |

**Example**
`GET http://localhost:8080/accounts/getbyid/1`

**Response**
`HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 26 May 2023 14:18:04 GMT
Connection: close
{
"id": 2,
"balance": 1000.00,
"account_number": "A002"
}`

### Endpoint 5: Delete an account using the ID
**Url**
`http://localhost:8080/accounts/deletebyid/{id}`

**Parameters**

| PARAMETER | TYPE | DESCRIPTION                   |
|-----------|------|-------------------------------|
| id        | int  | Account id you want to delete |

**Example**
`DELETE http://localhost:8080/accounts/deletebyid/1`

**Response**
`HTTP/1.1 200
Content-Type: text/plain;charset=UTF-8
Content-Length: 42
Date: Fri, 26 May 2023 14:18:49 GMT
Connection: close
The account with the id 1 has been deleted`

### Endpoint 6: Delete an account using the account number
**Url**
`http://localhost:8080/accounts/deletebyaccountnumber/{account_number}`

**Parameters**

| PARAMETER       | TYPE   | DESCRIPTION                       |
|-----------------|--------|-----------------------------------|
| account_number  | string | Account number you want to delete |

**Example**
`DELETE http://localhost:8080/accounts/deletebyaccountnumber/A002`

**Response**
`HTTP/1.1 200
Content-Type: text/plain;charset=UTF-8
Content-Length: 53
Date: Fri, 26 May 2023 14:20:39 GMT
Connection: close
The account with account_number A002 has been deleted`

## Transfers

### Endpoint 7: Return a list with all transfers
**Url**
`http://localhost:8080/transfers/alltransfers`

**Parameters**
*No additional parameters required*

**Example**
`GET http://localhost:8080/transfers/alltransfers`

**Response**
`HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 26 May 2023 14:28:01 GMT
Connection: close
[
{
"id": 1,
"amount": 100.00,
"source_account_id": 3,
"destination_account_id": 4
},
{
"id": 2,
"amount": 100.00,
"source_account_id": 3,
"destination_account_id": 5
},
{
"id": 3,
"amount": 200.00,
"source_account_id": 4,
"destination_account_id": 3
},
{
"id": 4,
"amount": 200.00,
"source_account_id": 4,
"destination_account_id": 5
},
{
"id": 5,
"amount": 300.00,
"source_account_id": 5,
"destination_account_id": 3
},
{
"id": 6,
"amount": 300.00,
"source_account_id": 5,
"destination_account_id": 4
}
]`

### Endpoint 8: Return a transfer searched by the transfer ID
**Url**
`http://localhost:8080/transfers/transferbyid/{id}`

**Parameters**

| PARAMETER | TYPE | DESCRIPTION                     |
|-----------|------|---------------------------------|
| id        | int  | Transfer id you want to consult |

**Example**
`GET http://localhost:8080/transfers/transferbyid/1`

**Response**
`HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 26 May 2023 14:34:01 GMT
Connection: close
{
"id": 1,
"amount": 100.00,
"source_account_id": 3,
"destination_account_id": 4
}`

### Endpoint 9: Return a transfers list related to an account id
**Url**
`http://localhost:8080/transfers/transfersbyaccountid/{accountid}`

**Parameters**

| PARAMETER | TYPE | DESCRIPTION                                  |
|-----------|------|----------------------------------------------|
| id        | int  | Account id you want to consult the transfers |

**Example**
`GET http://localhost:8080/transfers/transfersbyaccountid/4`

**Response**
`HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 26 May 2023 14:37:50 GMT
Connection: close
[
{
"id": 1,
"amount": 100.00,
"source_account_id": 3,
"destination_account_id": 4
},
{
"id": 3,
"amount": 200.00,
"source_account_id": 4,
"destination_account_id": 3
},
{
"id": 4,
"amount": 200.00,
"source_account_id": 4,
"destination_account_id": 5
},
{
"id": 6,
"amount": 300.00,
"source_account_id": 5,
"destination_account_id": 4
}
]`

### Endpoint 10: Make a transfer
**Url**
`http://localhost:8080/transfers/maketransfer`

**Parameters**

| PARAMETER               | TYPE    | DESCRIPTION                   |
|-------------------------|---------|-------------------------------|
| source_account_id       | int     | Id of the source account      |
| destination_account_id  | int     | Id of the destination account |
| amount                  | decimal | Transfer amount               |

**Example**
`POST http://localhost:8080/transfers/maketransfer
Content-Type: application/json
{
"source_account_id": 3,
"destination_account_id": 4,
"amount":100.0
}`

**Response**
`HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 26 May 2023 14:42:25 GMT
Connection: close
{
"id": 7,
"amount": 100.0,
"source_account_id": 3,
"destination_account_id": 4
}`

## Contact
If you have any questions or suggestions, please contact me:
- **email**: [wilmeryes96@yahoo.es]