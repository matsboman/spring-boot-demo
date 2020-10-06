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
