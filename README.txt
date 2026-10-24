RUNNING GUIDE
================

- Questions 1-12: standalone Java programs; compile with javac and run with java.
- Questions 13-14: separate client and server programs; run them in separate terminals.
- Questions 15-17: MySQL + JDBC; MySQL Connector/J JAR is in Database-JDBC/lib/.
- Questions 18-20: separate Spring Boot Maven projects.


GENERAL JAVA PROGRAMS (QUESTIONS 1-12)
=======================================
Folder: Core-Java/
Compile and run inside Core-Java/.

For each program, compile and run:

    javac Program.java && java Program

Or:

    java Program.java


TCP ECHO APPLICATION (CLIENT + SERVER)
==========================================
Folder: TCPEcho/

Compile both files from inside TCPEcho/:

    javac EchoServer.java EchoClient.java

Terminal 1 - start the server first:

    java EchoServer

Terminal 2 - start the client:

    java EchoClient

Type messages in the client terminal.


MULTICLIENT CHAT SERVER
===========================
Folder: MultiClientChat/

Compile both files from inside MultiClientChat/:

    javac MultiClientChatServer.java ChatClient.java

Terminal 1 - start the server:

    java MultiClientChatServer

Terminal 2 - start client 1:

    java ChatClient

Terminal 3 - start client 2:

    java ChatClient

Open more client terminals to connect additional users. Messages sent by one client are broadcast to connected clients. Type exit in one client to leave; the server should continue running for the remaining clients. Use Ctrl+C in the server terminal to stop the server when finished.


JDBC SETUP FOR QUESTIONS 15-17
==============================
Folder: Database-JDBC/

A. MySQL driver and classpath
-----------------------------
The driver JAR is in lib/.

Run commands from inside Database-JDBC/:

    javac -cp ".;lib/*" Program.java
    java  -cp ".;lib/*" Program

Or directly without compiling using:

    java -cp "lib/*" Program.java

';' separates classpath entries. The '.' means the current directory (where the compiled class is found); lib/* includes JAR files inside lib/.

The Java URL, username and password must match the database that is actually running. For example:

    jdbc:mysql://localhost:3306/db_name


B. Create the JDBC databases and tables
---------------------------------------

CREATE DATABASE db_name;
USE db_name;

QUESTION 15 - StudentRecordManagement

    CREATE TABLE students (
        id INT PRIMARY KEY,
        name VARCHAR(50) NOT NULL,
        age INT,
        course VARCHAR(50)
    );


QUESTION 16 - EmployeeSearchReporting

    CREATE TABLE employees (
        id INT PRIMARY KEY,
        name VARCHAR(50),
        department VARCHAR(50),
        salary DECIMAL(10,2)
    );
    INSERT INTO employees VALUES
        (101, 'Arun', 'IT', 45000),
        (102, 'Meera', 'HR', 35000),
        (103, 'Rahul', 'IT', 60000),
        (104, 'Anjali', 'Sales', 40000);

QUESTION 17 - BankTransferTransaction

    CREATE TABLE accounts (
        id INT PRIMARY KEY,
        name VARCHAR(50),
        balance DECIMAL(10,2) NOT NULL
    );
    INSERT INTO accounts VALUES
        (101, 'Arun', 1000.00),
        (102, 'Meera', 500.00);


SPRING BOOT PROJECT SETUP (QUESTIONS 18-20)
===========================================
Official site https://start.spring.io/ or the Spring Initializr extension can be used to create each Spring project separately.

Recommended common settings:
  Project: Maven
  Language: Java
  Packaging: Jar
  Java: choose an installed supported version


QUESTION 18 - Billing / Constructor Dependency Injection
--------------------------------------------------------
Folder: Spring Framework/billing/
Main class: BillingApplication
Package: com.example.billing
Initializr dependency: No extra dependencies needed.

Run from the billing project directory:

    ./mvnw spring-boot:run

Expected application output includes:

    Order placed.
    Payment processed: 500.0

Spring Boot also print other startup logs too.

QUESTION 19 - Student REST API (in-memory collection)
-----------------------------------------------------
Folder: Spring Framework/student-rest-api/
Main class: StudentRestApiApplication
Package: com.example.studentapi
Initializr dependency: Spring Web

Run from the student-rest-api project directory:

    ./mvnw spring-boot:run

The app normally listens on http://localhost:8080. Keep this terminal running and use a second terminal for curl commands. If port 8080 is occupied, stop the other app or set server.port in application.properties.

Add a student:

    curl --json '{"id":1,"name":"Arun","course":"MSc CS"}' http://localhost:8080/students

List students:

    curl http://localhost:8080/students

Retrieve student 1:

    curl http://localhost:8080/students/1

Update student 1:

    curl -X PUT --json '{"id":1, "name":"Arun Kumar","course":"MCA"}' http://localhost:8080/students/1

Delete student 1:

    curl -X DELETE http://localhost:8080/students/1

The student API uses an in-memory HashMap. Records disappear when the application restarts.

If curl does not support --json, use the equivalent JSON header and data flags, e.g.:

    curl -X POST http://localhost:8080/students -H 'Content-Type: application/json' -d '{"id":1,"name":"Arun","course":"MSc CS"}'

QUESTION 20 - Product Management with JdbcTemplate
--------------------------------------------------
Folder: Spring Framework/product-management/
Main class: ProductManagementApplication
Package: com.example.product_management
Initializr dependencies:
  Spring Web
  JDBC API / Spring JDBC starter (select the JDBC dependency offered by Initializr)
  MySQL Driver


Database setup (run in MySQL):

    CREATE TABLE products (
        id INT PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        price DECIMAL(10,2) NOT NULL
    );
    INSERT INTO products VALUES (1, 'Keyboard', 1200.00);

Configure Spring Framework/product-management/src/main/resources/application.properties:

    spring.datasource.url=jdbc:mysql://localhost:3306/db_name
    spring.datasource.username=user
    spring.datasource.password=password

Use the actual server host/port and your database credentials.

Run from the product-management project directory:

    ./mvnw spring-boot:run

Keep it running and test from another terminal:

Add a product:

    curl --json '{"id":2,"name":"Mouse","price":500}' http://localhost:8080/products

List all products:

    curl http://localhost:8080/products

Retrieve product 2:

    curl http://localhost:8080/products/2

Update product 2:

    curl -X PUT --json '{"id":2, "name":"Wireless Mouse","price":800}' http://localhost:8080/products/2

Delete product 2:

    curl -X DELETE http://localhost:8080/products/2

Verify persisted records directly in MySQL.


SCREENSHOTS
=============
Ouput screenshots are in /screenshots