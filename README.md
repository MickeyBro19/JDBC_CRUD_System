# 📚 JDBC Student Management System

A simple Java-based console application that performs CRUD (Create, Read, Update, Delete) operations using JDBC and PostgreSQL.

---

## 🚀 Features

* Add new students
* View all students
* Update student details
* Delete student records
* Clean layered architecture (Model, DAO, Utility)

---

## 🛠️ Tech Stack

* Java (Core + JDBC)
* PostgreSQL
* SQL

---

## 📂 Project Structure

```
com.mickey
│
├── model
│   └── Student.java
│
├── dao
│   └── StudentDAO.java
│
├── util
│   └── DBConnection.java
│
└── Main.java
```

---

## ⚙️ Setup Instructions

1. Clone the repository:

```
git clone <your-repo-link>
```

2. Create PostgreSQL database:

```
CREATE DATABASE student_db;

CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    age INT
);
```

3. Update database credentials in:

```
DBConnection.java
```

4. Run the application:

```
Main.java
```

---

## 📸 Sample Output

```
1. Add  2. View  3. Update  4. Delete  5. Exit
```

---

## 🧠 What I Learned

* JDBC connection handling
* PreparedStatement vs Statement
* CRUD operations with SQL
* Layered architecture basics

---

## 🔮 Future Improvements

* Add input validation
* Implement logging
* Convert to REST API using Spring Boot
* Add DTO and service layer

---

## ✨ Author

Mickey
