#!/bin/bash

API_ACCOUNTS="http://localhost:8080/accounts"
API_TRANSFERS="http://localhost:8080/transfers"


echo "ACCOUNTS"

# GET
echo "GET all empty"
curl -X GET $API_ACCOUNTS"/allaccounts"
echo

# POST
echo "POST"
curl -X POST -H "Content-Type: application/json" -d '{"balance":1000.00, "account_number":"AC001"}' $API_ACCOUNTS"/create"
echo
curl -X POST -H "Content-Type: application/json" -d '{"balance":1000.00, "account_number":"AC002"}' $API_ACCOUNTS"/create"
echo
curl -X POST -H "Content-Type: application/json" -d '{"balance":1000.00, "account_number":"AC003"}' $API_ACCOUNTS"/create"
echo
curl -X POST -H "Content-Type: application/json" -d '{"balance":1000.00, "account_number":"AC004"}' $API_ACCOUNTS"/create"
echo
curl -X POST -H "Content-Type: application/json" -d '{"balance":1000.00, "account_number":"AC005"}' $API_ACCOUNTS"/create"
echo

# GET
echo "GET all"
curl -X GET $API_ACCOUNTS"/allaccounts"
echo

echo "GET by id"
curl -X GET $API_ACCOUNTS"/getbyid/1"
echo

echo "GET by account_number"
curl -X GET $API_ACCOUNTS"/getbyaccountnumber/AC002"
echo


# DELETE
echo "DELETE by id"
curl -X DELETE $API_ACCOUNTS"/deletebyid/1"
echo

echo "DELETE by account_number"
curl -X DELETE $API_ACCOUNTS"/deletebyaccountnumber/AC002"
echo

# GET
echo "GET by account_number"
curl -X GET $API_ACCOUNTS"/getbyaccountnumber/AC001"
echo

echo "GET by id"
curl -X GET $API_ACCOUNTS"/getbyid/2"
echo



echo "TRANSFERS"

# GET
echo "GET all empty"
curl -X GET $API_TRANSFERS"/alltransfers"
echo

# POST
echo "POST"
curl -X POST -H "Content-Type: application/json" -d '{"source_account_id":3, "destination_account_id":4, "amount":100.0}' $API_TRANSFERS"/maketransfer"
echo
curl -X POST -H "Content-Type: application/json" -d '{"source_account_id":3, "destination_account_id":5, "amount":100.0}' $API_TRANSFERS"/maketransfer"
echo
curl -X POST -H "Content-Type: application/json" -d '{"source_account_id":4, "destination_account_id":3, "amount":200.0}' $API_TRANSFERS"/maketransfer"
echo
curl -X POST -H "Content-Type: application/json" -d '{"source_account_id":4, "destination_account_id":5, "amount":200.0}' $API_TRANSFERS"/maketransfer"
echo
curl -X POST -H "Content-Type: application/json" -d '{"source_account_id":5, "destination_account_id":3, "amount":300.0}' $API_TRANSFERS"/maketransfer"
echo
curl -X POST -H "Content-Type: application/json" -d '{"source_account_id":5, "destination_account_id":4, "amount":300.0}' $API_TRANSFERS"/maketransfer"
echo
curl -X POST -H "Content-Type: application/json" -d '{"source_account_id":5, "destination_account_id":4, "amount":800.0}' $API_TRANSFERS"/maketransfer"
echo
curl -X POST -H "Content-Type: application/json" -d '{"source_account_id":1, "destination_account_id":4, "amount":100.0}' $API_TRANSFERS"/maketransfer"
echo
curl -X POST -H "Content-Type: application/json" -d '{"source_account_id":5, "destination_account_id":2, "amount":100.0}' $API_TRANSFERS"/maketransfer"
echo

# GET
echo "GET all transfers"
curl -X GET $API_TRANSFERS"/alltransfers"
echo

echo "GET all accounts"
curl -X GET $API_ACCOUNTS"/allaccounts"
echo

echo "GET transfer by id 6"
curl -X GET $API_TRANSFERS"/transferbyid/6"
echo

echo "GET transfers by account_id"
curl -X GET $API_TRANSFERS"/transfersbyaccountid/3"
echo




