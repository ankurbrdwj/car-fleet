Car fleet project # clevershuttle

Build and run this project use:


  gradle bootRun

You can curl or import collection in postman :


POST

curl --location --request POST 'localhost:8080/cars' \
--header 'Content-Type: application/json' \
--data-raw '{
"brand": "Flexa",
"licensePlate": "L-CS8877E",
"manufacturer": "Ferrari",
"operationsCity": "Berlin",
"status": "available"
}'


curl --location --request POST 'localhost:8080/cars' \
--header 'Content-Type: application/json' \
--data-raw '{
"brand": "Benz",
"licensePlate": "L-DD4455J",
"manufacturer": "Mercedez",
"operationsCity": "Munich",
"status": "available"
}'

GETALL

curl --location --request GET 'localhost:8080/cars'


GET ONE

curl --location --request GET 'localhost:8080/cars/1'


PUT

curl --location --request PUT 'localhost:8080/cars/1' \
--header 'Content-Type: application/json' \
--data-raw '{
"brand": "Flexa",
"licensePlate": "L-CS8877E",
"manufacturer": "Ferrari",
"operationsCity": "Munich",
"status": "available"
}'


DEL

curl --location --request DELETE 'localhost:8080/cars/1'

