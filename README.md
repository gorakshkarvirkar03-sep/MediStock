\# MediStock – Medicine Inventory Management System



MediStock is a Java and MySQL-based medicine inventory management application designed to help manage medicines, suppliers, purchases, and sales efficiently.



\## Features



\- Medicine inventory management

\- Add, view, update, and delete medicines

\- Supplier management

\- MySQL database connectivity using JDBC

\- Medicine and supplier data access using DAO classes

\- Service-layer business logic



\## Technology Stack



\- \*\*Programming Language:\*\* Java

\- \*\*Database:\*\* MySQL

\- \*\*Database Connectivity:\*\* JDBC

\- \*\*IDE:\*\* Visual Studio Code

\- \*\*Version Control:\*\* Git and GitHub



\## Project Structure



```text

MediStock/

├── src/

│   ├── dao/

│   ├── model/

│   ├── service/

│   ├── util/

│   └── Test\*.java

├── lib/

│   └── mysql-connector-j-26.10.0.jar

├── .gitignore

└── README.md

```



\## Database Configuration



Create a MySQL database named `medistock\_db`.



Configure these environment variables before running the application:



\- `MEDISTOCK\_DB\_USER`

\- `MEDISTOCK\_DB\_PASSWORD`



The application reads database credentials from environment variables instead of storing passwords in source code.



\## How to Run



1\. Install Java JDK and MySQL.

2\. Configure the MySQL database.

3\. Configure the database environment variables.

4\. Compile the Java source files with the MySQL Connector/J library.

5\. Run the required Java test or application class.



\## Project Status



Currently under development.



\## Author


Hey guys while building this project today my mind gets burst out the login page is unable to connect the dashboard I don't know the error yet, but today i really got burst, even the chatgpt also can understand it from the last 1 hr i was doing that only but failed.Buy guys.

Goraksh Karvirkar
