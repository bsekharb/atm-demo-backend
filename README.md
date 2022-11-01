# ATM Simulator
This is a Spring Boot project simulating ATM machine's dispensing and bank account maintenance logic

### About API
The ATM Simulator provides two services.
* Dispense service - To dispense money from the ATM Simulator if customer enter correct pin number and deduct the amount from customer account and update the balance amount and update the cash in atm.
* Check balance service - To check current amount of each type of notes the ATM Simulator

* loadBankCash -To load cash in atm
* createBankAccount - To create customer account
* customer account balance - To check customer account balance

### Technologies
* Maven - Software project management
* Spring Boot]- Quickly create stand-alone, production-grade Spring based Applications
* H2 - A Java in-memory database providing JDBC API
* junit,Mockito - Write beautiful tests with a clean & readable with simple API

Git url:
*https://github.com/bsekharb/atm-demo-backend.git
* Maven - you can easily install it on MAC OS X by 
```
$ brew install maven
```

### Running project on MAC OS X
* Clone and go to the project by running command
```
$ git clone git@github.com:bsekharb/atm-demo-backend.git or git clone https://github.com/bsekharb/atm-demo-backend.git 
$ cd cd atm-demo-backend
```

* Then prepare project dependencies with
```
$ mvn clean install
```

* Run project using command
```
$ mvn spring-boot:run
```

The project should start on port 8080

Mandatory steps to test:

you have to create the bank account for the customer using below POST API:
---------------------------------------------
url: http://localhost:8080/api/init-bankaccount Action: POST
Body: JSON
1st customer:
{
"pin":1234,
"accountNumber":123456789,
"openingBalance":800,
"overDraft":200
}

2nd customer:
{
"pin":4321,
"accountNumber":987654321,
"openingBalance":1230,
"overDraft":150
}
-----------------------------------------
You have to load the cash via postman using below POST API:
-------------------------------------
url: http://localhost:8080/api/init-bankcash Action: POST
Body: JSON
1st cash load:
{
    "type": "fifty",
    "value": 50,
    "amount": 10
}
2nd cash load:
{
    "type": "twenty",
    "value": 20,
    "amount": 30
}
3rd cash load:
{
    "type": "ten",
    "value": 10,
    "amount": 30
}
4nd cash load:
{
    "type": "five",
    "value": 5,
    "amount": 20
}

-------------------------------------------------------------
To get the customer account info via [postman] or by opening the following URL in browser

http://localhost:8080/api/bankaccount

[
    {
        "pin": 1234,
        "accountNumber": 123456789,
        "openingBalance": 800,
        "overDraft": 200
    },
    {
        "pin": 4321,
        "accountNumber": 987654321,
        "openingBalance": 1230,
        "overDraft": 150
    }
]
-----------------------------------------------------------------

-------------------------------------------------------------
To get the cash info in atm via [postman] or by opening the following URL in browser

http://localhost:8080/api/bankcash

[
    {
        "value": 5,
        "type": "five",
        "amount": 20
    },
    {
        "value": 10,
        "type": "ten",
        "amount": 30
    },
    {
        "value": 20,
        "type": "twenty",
        "amount": 30
    },
    {
        "value": 50,
        "type": "fifty",
        "amount": 10
    }
]
-----------------------------------------------------------------

You can request dispensing money via [Postman](https://www.getpostman.com) or by opening the following URL in a browser
```
http://localhost:8080/api/dispense?amount=100&pin=1234
```
while \<AMOUNT\> indicates money you want to dispense from ATM Simulator
<br/>The response should look like
```
{
    "responseCode": "0",
    "responseDesc": "SUCCESS",
    "responseStatus": "SUCCESS",
    "responseBody": [
        {
            "type": "FIFTY",
            "value": 50,
            "amount": 2
        }
    ]
}
```
#### Possible API response
* Check balance in ATM success
```
http://localhost:8080/api/checkBalance
```

* Dispensing success
```
{
    "responseCode": "0",
    "responseDesc": "SUCCESS",
    "responseStatus": "SUCCESS",
    "responseBody": [
        {
            "type": "TEN",
            "value": 10,
            "amount": 30
        },
        {
            "type": "TWENTY",
            "value": 20,
            "amount": 30
        },
        {
            "type": "twenty",
            "value": 5,
             "amount": 20
        },
        {
            "type": "FIFTY",
            "value": 50,
            "amount": 8
        }
    ]
}
----
Pin is not matching with customer while withdrawn from atm
{
    "responseCode": "1",
    "responseDesc": "Pin is Incorrect!",
    "responseStatus": "FAIL",
    "responseBody": []
}
----
To check the Account balance for customer if pin is incorrect
http://localhost:8080/api/accountbalance/{pin}

{
    "responseCode": "1",
    "responseDesc": "unable to check your balance due to Pin is Incorrect!",
    "responseStatus": "FAIL",
    "responseBody": []
}
```
----
To check the Account balance for customer if pin is correct
http://localhost:8080/api/accountbalance/{pin}
{
    
    "responseCode": "0",
    "responseDesc": "Account Balance : 800",
    "responseStatus": "SUCCESS",
    "responseBody": []
}
-----
* Insufficient balance - Total balance of ATM Simulator is less then needed amount
```
{
    "responseCode": "1",
    "responseDesc": "Remaining balance less than dispensed amount",
    "responseStatus": "FAIL",
    "responseBody": []
}
```

* Invalid amount - Needed amount is less than minimum value of the note in ATM Simulator(which, in this case, is 4)
```
{
    "responseCode": "1",
    "responseDesc": "Amount less than min amount",
    "responseStatus": "FAIL",
    "responseBody": []
}
```

* Insufficient note - ATM Simulator doesn't have enough notes needed by requested amount or the simulator cannot find appropriate combination of notes to fulfill requested amount
```
{
    "responseCode": "1",
    "responseDesc": "Insufficient note number. Try dispensing a different amount.",
    "responseStatus": "FAIL",
    "responseBody": []
}
```


