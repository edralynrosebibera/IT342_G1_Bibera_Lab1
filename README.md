# User Registration & Authentication System

A full-stack web application that allows users to register, login, and manage their profile securely. This project uses a **Spring Boot backend** and a **React frontend**.

---

## 📋 Table of Contents

* Project Overview
* Features
* Tech Stack
* Project Structure
* Prerequisites
* Installation & Setup
* Running the Application
* API Endpoints
* Notes
* Author

---

## 🎯 Project Overview

This system provides a secure authentication process where users can:

* Register an account
* Login using credentials
* Access protected pages
* View and update their profile
* Logout securely

The system ensures unauthorized users cannot access protected routes.

---

## ✨ Features

### 🔐 Authentication

* User registration with validation
* Login with email and password
* Password encryption using BCrypt
* Logout functionality

### 👤 Profile Management

* View user profile
* Update profile information
* Linked user and profile (one-to-one)

### 🔒 Security

* Spring Security configuration
* CORS enabled for frontend
* Protected API routes

---

## 🛠️ Tech Stack

### Backend

* Spring Boot
* Java
* MySQL
* JPA / Hibernate
* Spring Security

### Frontend

* React
* JavaScript
* CSS

---

## 📁 Project Structure

```bash
IT342_G1_Bibera_Lab1/
│
├── backend/
│   ├── src/main/java/com/backend/backend/
│   │   ├── config/
│   │   │   └── SecurityConfig.java
│   │   │
│   │   ├── controller/
│   │   │   ├── AuthController.java
│   │   │   └── ProfileController.java
│   │   │
│   │   ├── dto/
│   │   │   ├── AuthResponse.java
│   │   │   ├── LoginRequest.java
│   │   │   └── RegisterRequest.java
│   │   │
│   │   ├── model/
│   │   │   ├── User.java
│   │   │   └── Profile.java
│   │   │
│   │   ├── repository/
│   │   │   ├── UserRepository.java
│   │   │   └── ProfileRepository.java
│   │   │
│   │   ├── service/
│   │   │   ├── UserService.java
│   │   │   ├── ProfileService.java
│   │   │   └── TokenProvider.java
│   │   │
│   │   └── BackendApplication.java
│   │
│   ├── src/main/resources/
│   │   └── application.properties
│   │
│   └── pom.xml
│
├── web/
│   ├── public/
│   │   ├── index.html
│   │   └── assets
│   │
│   ├── src/
│   │   ├── components/
│   │   │   ├── ExamCard.js
│   │   │   ├── NavLink.js
│   │   │   ├── ProfileMenu.js
│   │   │   └── StatsBar.js
│   │   │
│   │   ├── pages/
│   │   │   ├── Dashboard.js
│   │   │   ├── Login.js
│   │   │   └── Signup.js
│   │   │
│   │   ├── services/
│   │   │   └── authService.js
│   │   │
│   │   ├── App.js
│   │   └── index.js
│   │
│   └── package.json
│
├── mobile/ (optional / placeholder)
│
└── README.md
```

---

## 📦 Prerequisites

* Java 17 or higher
* Node.js 18 or higher
* MySQL
* Maven

---

## 🚀 Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/IT342_G1_Bibera_Lab1.git
cd IT342_G1_Bibera_Lab1
```

---

### 2. Backend Setup

#### Create Database

```sql
CREATE DATABASE user_auth_db;
```

#### Configure application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/user_auth_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
server.port=8080
```

#### Run Backend

```bash
cd backend
mvn spring-boot:run
```

---

### 3. Frontend Setup

```bash
cd web
npm install
npm start
```

---

## ▶️ Running the Application

* Backend: http://localhost:8080
* Frontend: http://localhost:3000

---

## 📡 API Endpoints

### 🔐 Authentication

#### Register

POST `/api/auth/register`

#### Login

POST `/api/auth/login`

#### Logout

POST `/api/auth/logout`

---

### 👤 Profile

#### View Profile

GET `/api/profile/{userId}`

#### Update Profile

PUT `/api/profile/{userId}`

---

## 📌 Notes

* Make sure MySQL server is running
* Fix database credentials if connection fails
* Run backend before frontend
* Use correct port numbers

---

## 👨‍💻 Author

* Course: IT342
* Section: G1
* Developer: Edralyn Rose P. Bibera
