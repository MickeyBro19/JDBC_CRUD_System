# 🎓 Student Management System (Core Java Backend Simulation)

## 🚀 Overview

This project is a **console-based backend system built using Core Java + JDBC**, designed to simulate how modern backend frameworks like Spring Boot work — **without actually using Spring**.

The goal is not just CRUD operations, but to **understand what happens under the hood** in real-world backend applications.

---

## 🧠 Objective

Instead of relying on frameworks, this project manually implements:

- Layered Architecture
- Connection Pooling
- Transaction Management
- Logging System
- External Configuration Handling
- DAO Pattern

👉 This builds strong backend fundamentals and removes "magic confusion" when using Spring later.

---

## 🏗️ Architecture Overview


Main (CLI)
↓
Service Layer (Business Logic)
↓
DAO Layer (Database Access)
↓
Connection Pool
↓
PostgreSQL Database


---

## ⚙️ Tech Stack

- Java (JDK 17+ / 26 compatible)
- JDBC
- PostgreSQL
- Multithreading
- File Handling (for logging & config)

---

## 🧩 Key Features

### ✅ CRUD Operations
- Add, View, Update, Delete students

### ✅ Bulk Insert (Batch Processing)
- Efficient insertion using batch queries
- Performance comparison support

### ✅ Connection Pooling
- Custom-built connection pool
- Limits max DB connections
- Thread-safe resource management

### ✅ Transaction Management
- Manual commit & rollback handling
- Ensures data integrity (especially in bulk operations)

### ✅ Logging System
- Centralized logging utility (`AppLogger`)
- Tracks:
  - Service layer operations
  - DAO/database interactions
  - Errors and failures

### ✅ External Configuration
- Database config stored in `config.properties`
- Loaded dynamically using classpath
- No hardcoded credentials

---

## 🔍 Separation of Concerns (Core Learning)

This project strictly follows layered separation:

### 🧠 1. Main Layer (CLI / Entry Point)
- Handles user input/output
- No business logic
- Delegates tasks to Service layer

---

### ⚙️ 2. Service Layer (Business Logic)
- Validates data
- Controls application flow
- Logs business-level actions
- Calls DAO layer

👉 Example:
- Prevent negative age
- Track operations like "add student"

---

### 🗄️ 3. DAO Layer (Data Access)
- Handles all database interactions
- Executes SQL queries
- Converts ResultSet → Objects
- Logs DB-level events

👉 Pure persistence logic only

---

### 🔌 4. Connection Layer
- Manages database connections
- Implements custom **Connection Pool**
- Handles:
  - Allocation
  - Reuse
  - Thread synchronization

---

### ⚡ 5. Utility Layer

#### 📌 ConfigLoader
- Loads external configuration
- Enables environment-based setup

#### 📌 AppLogger
- Centralized logging system
- Handles:
  - Info logs
  - Error logs

---

## 🔄 How This Mimics Spring Boot

| Feature                | This Project (Manual)     | Spring Boot Equivalent        |
|----------------------|--------------------------|------------------------------|
| Config file          | `config.properties`      | `application.properties`     |
| Dependency handling  | Manual object creation   | `@Autowired`                 |
| Layered architecture | Explicit separation      | `@Service`, `@Repository`    |
| Transactions         | Manual commit/rollback   | `@Transactional`             |
| Connection pool      | Custom implementation    | HikariCP (default)           |
| Logging              | Custom logger            | Logback / SLF4J              |

---

## 🧪 How to Run

1. Create PostgreSQL DB:
```sql
CREATE DATABASE Student;
Create table:
CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    age INT
);
```
Update config:
```
db.url=jdbc:postgresql://localhost:5432/Student
db.user=postgres
db.password=your_password
```
- Run Main.java
## 🧪 Sample Features to Test
- Add student
- Bulk insert (performance check)
- Multi-threaded connection pool test
- Clear table
- Trigger DB failure (for logging)
## 📈 Learning Outcomes

- By building this system, I gained:
    - Deep understanding of backend architecture
    - JDBC and database interaction
    - Thread-safe connection management
    - Transaction handling
    - Logging strategies
    - Config-driven applications
##🚀 Future Enhancements
- REST API layer (Spring Boot)
- DTO & Mapper integration
- Authentication system
- Dockerized setup
- Replace custom logger with SLF4J
##💡 Key Insight

- Frameworks don’t make things simple.
- They hide complexity.

- This project focuses on understanding that hidden complexity.

## 👨‍💻 Author

Mickey – Backend Developer in Progress 🚀
