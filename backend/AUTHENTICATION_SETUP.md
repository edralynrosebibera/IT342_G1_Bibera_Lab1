# Spring Boot Register & Login Backend Setup Guide

## Prerequisites
- Java 17+
- XAMPP (MySQL)
- Maven
- Spring Boot 4.0.2

## Database Setup (XAMPP MySQL)

### Step 1: Start XAMPP
1. Open XAMPP Control Panel
2. Click "Start" next to Apache and MySQL

### Step 2: Create Database
1. Open browser and go to: `http://localhost/phpmyadmin`
2. Click on "SQL" tab
3. Paste the following command and execute:

```sql
CREATE DATABASE auth_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

That's it! The `users` table will be created automatically when you run the application.

## Project Structure
```
backend/
├── src/main/java/com/backend/backend/
│   ├── BackendApplication.java
│   ├── config/
│   │   └── SecurityConfig.java
│   ├── controller/
│   │   └── UserController.java
│   ├── dto/
│   │   ├── AuthResponse.java
│   │   ├── LoginRequest.java
│   │   └── RegisterRequest.java
│   ├── model/
│   │   └── User.java
│   ├── repository/
│   │   └── UserRepository.java
│   └── service/
│       └── UserService.java
├── src/main/resources/
│   └── application.properties
└── pom.xml
```

## API Endpoints

### 1. Register User
- **URL:** `POST http://localhost:8080/api/auth/register`
- **Request Body:**
```json
{
    "email": "user@example.com",
    "password": "password123",
    "confirmPassword": "password123",
    "firstName": "John",
    "lastName": "Doe"
}
```
- **Response (Success - 201):**
```json
{
    "success": true,
    "message": "User registered successfully",
    "userId": 1,
    "email": "user@example.com",
    "firstName": "John",
    "lastName": "Doe"
}
```
- **Response (Error - 400):**
```json
{
    "success": false,
    "message": "Email already exists"
}
```

### 2. Login User
- **URL:** `POST http://localhost:8080/api/auth/login`
- **Request Body:**
```json
{
    "email": "user@example.com",
    "password": "password123"
}
```
- **Response (Success - 200):**
```json
{
    "success": true,
    "message": "Login successful",
    "userId": 1,
    "email": "user@example.com",
    "firstName": "John",
    "lastName": "Doe"
}
```
- **Response (Error - 401):**
```json
{
    "success": false,
    "message": "Invalid email or password"
}
```

### 3. Test Endpoint
- **URL:** `GET http://localhost:8080/api/auth/test`
- **Response:** `Backend is running!`

## Running the Backend

### Option 1: Using Maven (Recommended)
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

### Option 2: Using IDE (IntelliJ IDEA)
1. Right-click on `BackendApplication.java`
2. Select "Run 'BackendApplication'"

### Option 3: Build and Run JAR
```bash
cd backend
mvn clean package
java -jar target/backend-0.0.1-SNAPSHOT.jar
```

## Connecting Frontend to Backend

Update your React frontend to call these endpoints. Example with `fetch`:

```javascript
// Register
const registerUser = async (userData) => {
    const response = await fetch('http://localhost:8080/api/auth/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(userData)
    });
    return await response.json();
};

// Login
const loginUser = async (credentials) => {
    const response = await fetch('http://localhost:8080/api/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(credentials)
    });
    return await response.json();
};
```

## Features Implemented

✅ User Registration with validation
✅ Password hashing with BCrypt
✅ User Login with password verification
✅ Email uniqueness validation
✅ Password confirmation check
✅ Minimum password length (6 characters)
✅ CORS enabled for React frontend
✅ RESTful API endpoints
✅ MySQL database integration

## Database Schema

The `users` table will be created with the following columns:
- `id` - Primary key (auto-increment)
- `email` - Unique email address
- `password` - Hashed password
- `first_name` - User's first name
- `last_name` - User's last name
- `created_at` - Timestamp of registration

## Troubleshooting

### Database Connection Error
- Ensure XAMPP MySQL is running
- Check `application.properties` for correct database credentials
- Default: mysql://localhost:3306/auth_db with username "root" and empty password

### Port Already in Use
- Change `server.port` in `application.properties` to a different port (e.g., 8081)

### Build Errors
- Run `mvn clean install` to refresh dependencies
- Ensure Java 17 is installed

## Security Notes

For production, consider:
- Using JWT tokens instead of simple response
- Implementing HTTPS
- Adding rate limiting
- Using environment variables for database credentials
- Implementing token-based authentication
