
# 🐟 Fish Waste Recycling System

## About The Project

Fish Waste Recycling System is a Spring Boot based web application that provides a digital platform to connect fish waste sellers and recycling companies.

In many places, fish waste is not managed properly and it becomes a cause of environmental pollution. This project helps to solve this problem by creating a marketplace where sellers can add their fish waste details and recycling companies can find and purchase waste according to their requirements.

The main objective of this project is to create a proper waste management system where fish waste can be collected, recycled, and converted into useful products.

---

## Features

## 👤 User Module

Users can register and login into the system.

The system supports different roles:

- SELLER
- COMPANY
- ADMIN

Each user has different responsibilities according to their role.

---

# 🐟 Seller Module

Seller can:

- Register as a seller.
- Login into the system.
- Add fish waste listings.
- View their waste listings.
- Update waste listing details.
- Delete waste listings.
- Track waste status.

Seller dashboard shows:

- Total Listings
- Reserved Listings
- Sold Listings

---

# 🏭 Recycling Company Module

Recycling companies can:

- Register as a company.
- Login into the system.
- View available fish waste listings.
- Check waste details.
- Select waste according to their requirements.
- Reserve and purchase fish waste.

---

# 📦 Waste Listing Module

Seller can create waste listings with details:

- Waste Type
- Quantity
- Price
- Location
- Description
- Waste Status

Waste status:

```

AVAILABLE
RESERVED
SOLD

```

---

# 🛠️ Technology Stack

## Backend

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- REST API
- Maven

## Database

- MySQL

## Tools Used

- IntelliJ IDEA
- Postman
- Git
- GitHub

---

# 🏗️ Project Architecture

This project follows a layered architecture.

```

Controller Layer
|
|
Service Layer
|
|
Repository Layer
|
|
Database

```

### Controller Layer

Handles incoming HTTP requests and sends responses.

### Service Layer

Contains business logic of the application.

### Repository Layer

Responsible for database operations using JPA.

---

# 📂 Project Structure

```

Fish-Waste-Recycle-System

│
├── controller
│
├── service
│
├── repository
│
├── entity
│
├── dto
│
├── exception
│
└── config

```

---

# 🗄️ Database Design

## User Entity

Stores user details.

Fields:

- id
- name
- email
- password
- phone number
- address
- role


Roles:

```

SELLER
COMPANY
ADMIN

```

---

## Waste Listing Entity

Stores fish waste details.

Fields:

- id
- waste type
- quantity
- price
- location
- description
- status
- seller information

---

# 🔗 REST API Modules

## User APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /users/register | Register new user |
| POST | /users/login | User login |


## Waste Listing APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /waste-listings | Create waste listing |
| GET | /waste-listings | Get all waste listings |
| GET | /waste-listings/{id} | Get listing by id |
| PUT | /waste-listings/{id} | Update listing |
| DELETE | /waste-listings/{id} | Delete listing |

---

# 🔄 Application Workflow

```

Seller
|
|
Creates Fish Waste Listing
|
|
Marketplace Platform
|
|
Company Searches Required Waste
|
|
Company Reserves/Purchases Waste
|
|
Listing Status Updated

```

---

# 🧪 API Testing

All REST APIs are tested using Postman.

Implemented operations:

- Create waste listing
- Fetch all waste listings
- Fetch waste listing by id
- Update waste listing
- Delete waste listing

---

# 🚀 Future Enhancements

Future improvements planned:

- JWT Authentication
- Role Based Authorization
- Online Payment Integration
- Email Notifications
- Company Requirement Matching
- Admin Dashboard
- Waste Quality Verification System
- Location Based Search

---

# ⭐ Project Highlights

- Developed using Spring Boot REST API.
- Implemented layered architecture.
- Used JPA/Hibernate for database interaction.
- Created CRUD operations for waste management.
- Designed a marketplace system between sellers and recycling companies.

---

# 👩‍💻 Developer

**Sandhyarani Patil**

Java Developer | Spring Boot Developer
```
