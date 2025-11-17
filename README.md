# CRUD_MyBatis3_Oracle

This is a working solution of Spring Boot MyBatis 3 using Oracle Database

##### To use this repository

1. Clone the repository
   
2. Configure oracle database (mine use XEPDB1 Oracle 21c), dont forget to create the table in a specific "SCHEMA" to your environment

3. Change the application.properties to use your database connection and change <SCHEMA_NAME> the mapper.xml file, the one that have SQL queries in it

4. mvn spring-boot:run in CMD console 1

5. Open CMD console 2 and run the curl command , refer the file "API" in 
