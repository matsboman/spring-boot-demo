# Build

clone the repo
cd demo
mvn clean install -DskipTests=true
mv target/demo-0.0.1-SNAPSHOT.jar target/user-mysql.jar

# Start
docker-compose up --build

# Stop
Ctrl-c
docker-compose down

# Run
From postman:

To upload csv files:A
POST:
http://localhost:8089/upload
Select form-data
key: file
value: browse to select your csv file, e.g. invoice_data_1.csv

To see the supplier summary
GET:
http://localhost:8089/listSupplierSummary?supplierId=supplier_1

# Test

Unfortunately you have to start mysql as a separate container when you run tests. I didn't have time to figure out how to get around that. Maybe an in memory db could be implemented here.

docker run --name mysql-standalone -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=test -e MYSQL_USER=sa -e MYSQL_PASSWORD=password -d mysql:5.6

You also need to change the property in application.properties:
from:
spring.datasource.url=jdbc:mysql://mysql-standalone:3306/test
to:
spring.datasource.url=jdbc:mysql://172.17.0.2:3306/test

The IP address in the url can be retrieved by:
docker inspect mysql-standalone | grep IPAddress

I haven't done JUnit for a while so I ran out of time and only had time to implement 1 test.
