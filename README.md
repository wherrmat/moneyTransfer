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
The test file makes Http queries to the API to test the endpoints and some exceptions
    - Attempt to obtain the list of accounts (empty)
    - Account creation
    - Query the list of accounts
    - Query accounts individually by id and account number
    - Delete individual accounts by id and account number
    - Attempt to query non-existent accounts by id and account number
    - Attempt to obtain transfer list (empty)
    - Making of transfers
    - Attempt of transfer without sufficient funds
    - Attempt of transfer with non-existent source account
    - Attempt of transfer with non-existent destination account
    - Query the transfer list
    - Query the list of accounts with the new balances
    - Query a transfer by id
    - Query the list of transfers related to an account id

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

| Field          | Type          | Description                              |
|----------------|---------------|------------------------------------------|
| id             | int           | ID of the account, unique, (PRIMARY KEY) |
| account_number | varchar(20)   | Personalized account number, unique      |
| balance        | decimal(10,2) | Current account balance                  |

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
`POST http://localhost:8080/accounts/create`

**Parameters**

| PARAMETER      | TYPE    | DESCRIPTION                    |
|----------------|---------|--------------------------------|
| balance        | decimal | Initial balance of the account |
| account_number | String  | Personalized account number    |

**Example**
`POST http://localhost:8080/accounts/create`
`Content-Type: application/json`
`{
    "balance": 1000.00,
    "account_number": "A001"
}`

- Código: 201 Created
- Tipo de contenido: application/json


Respuesta de error

Código: 404 Not Found
Tipo de contenido: text/plain
perl
Copy code
Usuario no encontrado.
Ejemplo de solicitud

- Endpoint 2: [Descripción del endpoint, ruta, método, parámetros, respuestas]
// Return a list with all accounts
@GetMapping(path = "/allaccounts")
public ResponseEntity<Object> getAllAccounts()

- Endpoint 3: [Descripción del endpoint, ruta, método, parámetros, respuestas]

- Endpoint 4: [Descripción del endpoint, ruta, método, parámetros, respuestas]

- Endpoint 5: [Descripción del endpoint, ruta, método, parámetros, respuestas]

- Endpoint 6: [Descripción del endpoint, ruta, método, parámetros, respuestas]


## Contacto

Si tienes alguna pregunta o sugerencia, no dudes en contactarme:

- Correo electrónico: [tuemail@example.com]
- [Enlaces a foros o canales de comunicación adicionales, si los hay]
Recuerda que este es solo un ejemplo básico y que puedes adaptar el contenido según las necesidades específicas de tu API y proyecto.






