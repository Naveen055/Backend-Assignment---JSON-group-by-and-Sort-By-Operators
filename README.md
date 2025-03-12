# JSON Group By and Sort By Operators

## Overview
JSON Group By and Sort By Operators is a Spring Boot REST API that allows users to store, retrieve, group, and sort JSON-based dataset records. The data is stored in PostgreSQL, and the application provides endpoints to insert, query, and organize datasets.

---

## Features
- Insert JSON records into datasets
- Retrieve records with grouping and sorting functionality
- Query a specific dataset using various parameters
- Uses PostgreSQL for data storage
- RESTful API built with Spring Boot

---

## Technologies Used
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Hibernate
- Maven
- Postman(For testing)

---

## Prerequisites
Ensure you have the following installed:
- Java 17+
- PostgreSQL
- Maven
- Git

---

## Getting Started

### Clone the repository
```bash
git clone https://github.com/your-username/json-groupby-sort.git
cd json-groupby-sort
```

---

### Configure PostgreSQL
1. Create a database in PostgreSQL:
```bash
CREATE DATABASE datasetdb;
```
2. Update `application.properties` (or `application.yml`) with your PostgreSQL credentials:
```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/datasetdb
spring.datasource.username=your_username
spring.datasource.password=your_password
```

---

### Build and Run
1. Clean and build the project:
```bash
mvn clean install
```
2. Run the application:
```bash
mvn spring-boot:run
```

---

## API Endpoints

### Insert a Record
- **Endpoint:** `POST /api/dataset/{datasetName}/record`
- **Request Body (JSON):**
```json
{
  "name": "Naveen",
  "age": 22,
  "city": "Bangalore"
}
```
- **Response:**
```json
{
  "recordId": 1,
  "dataset": "sampleDataset",
  "message": "Record added successfully"
}
```

---

### Query API
This API allows querying a specific dataset and performing group-by and sort-by operations on the JSON records.

#### 1. Group By
- **Endpoint:** `GET /api/dataset/{datasetName}/query?groupBy={field}`
- **Example:** `/api/dataset/sampleDataset/query?groupBy=city`
- **Response:**
```json
{
  "groupedRecords": {
    "Bangalore": [
      { "name": "Naveen", "age": 22, "city": "Bangalore" }
    ],
    "Mumbai": [
      { "name": "Raj", "age": 25, "city": "Mumbai" }
    ]
  }
}
```

#### 2. Sort By
- **Endpoint:** `GET /api/dataset/{datasetName}/query?sortBy={field}&order={asc/desc}`
- **Example:** `/api/dataset/sampleDataset/query?sortBy=age&order=desc`
- **Response:**
```json
{
  "sortedRecords": [
    { "name": "Raj", "age": 25, "city": "Mumbai" },
    { "name": "Naveen", "age": 22, "city": "Bangalore" }
  ]
}
```

#### 3. Query a Specific Dataset
- **Endpoint:** `GET /api/dataset/{datasetName}/query`
- **Example:** `/api/dataset/sampleDataset/query?groupBy=city&sortBy=age&order=asc`
- **Response:**
```json
{
  "groupedRecords": {
    "Bangalore": [
      { "name": "Naveen", "age": 22, "city": "Bangalore" }
    ],
    "Mumbai": [
      { "name": "Raj", "age": 25, "city": "Mumbai" }
    ]
  },
  "sortedRecords": [
    { "name": "Naveen", "age": 22, "city": "Bangalore" },
    { "name": "Raj", "age": 25, "city": "Mumbai" }
  ]
}
```

---

## Contact
For any questions or feedback, please reach out to:

- **Your Name**: Naveen  
- **Email**: [spnaveenkumar623@gmail.com](mailto:spnaveenkumar623@gmail.com)  
- **GitHub**: [Naveen055](https://github.com/Naveen055)

  ---
  
🚀 **Happy Coding!** 🎉 Keep building, keep learning, and never stop exploring new possibilities! 😃
