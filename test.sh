#!/bin/bash

API_ACCOUNTS="http://localhost:8080/accounts"
API_TRANSFERS="http://localhost:8080/transfers"


echo "ACCOUNTS"

# GET
echo "GET all empty"
curl_result=$(curl -X GET $API_ACCOUNTS"/allaccounts")
echo $curl_result

# POST
echo "POST"
curl_result=$(curl -X POST -H "Content-Type: application/json" -d '{"balance":1000.00, "account_number":"AC001"}' $API_ACCOUNTS"/create")
echo $curl_result
curl_result=$(curl -X POST -H "Content-Type: application/json" -d '{"balance":1000.00, "account_number":"AC002"}' $API_ACCOUNTS"/create")
echo $curl_result
curl_result=$(curl -X POST -H "Content-Type: application/json" -d '{"balance":1000.00, "account_number":"AC003"}' $API_ACCOUNTS"/create")
echo $curl_result
curl_result=$(curl -X POST -H "Content-Type: application/json" -d '{"balance":1000.00, "account_number":"AC004"}' $API_ACCOUNTS"/create")
echo $curl_result
curl_result=$(curl -X POST -H "Content-Type: application/json" -d '{"balance":1000.00, "account_number":"AC005"}' $API_ACCOUNTS"/create")
echo $curl_result

# GET
echo "GET all"
curl_result=$(curl -X GET $API_ACCOUNTS"/allaccounts")
echo $curl_result

echo "GET by id"
curl_result=$(curl -X GET $API_ACCOUNTS"/getbyid/1")
echo $curl_result

echo "GET by accoun_tnumber"
curl_result=$(curl -X GET $API_ACCOUNTS"/getbyaccountnumber/AC002")
echo $curl_result


# DELETE
echo "DELETE by id"
curl_result=$(curl -X DELETE $API_ACCOUNTS"/deletebyid/1")
echo $curl_result

echo "DELETE by accoun_tnumber"
curl_result=$(curl -X DELETE $API_ACCOUNTS"/deletebyaccountnumber/AC002")
echo $curl_result

echo "GET by accoun_tnumber"
curl_result=$(curl -X GET $API_ACCOUNTS"/getbyaccountnumber/AC001")
echo $curl_result

echo "GET by id"
curl_result=$(curl -X GET $API_ACCOUNTS"/getbyid/2")
echo $curl_result


