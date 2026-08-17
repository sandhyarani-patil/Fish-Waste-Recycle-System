
हो भाई. तुझ्या आतापर्यंतच्या **Fish Waste Recycle System** project flow, modules, JWT, role-based authorization, CRUD, order lifecycle, dashboard, validation, exception handling, Postman testing आणि MySQL यावर based एक **proper interview-level README.md** देतो.

# 🐟 Fish Waste Recycle System

A **Spring Boot REST API based Fish Waste Recycle Management System** that connects companies requiring fish waste with sellers who provide recyclable fish waste.

The system manages **users, sellers, companies, waste listings, requirements, orders, dashboards, authentication, authorization, validation and exception handling**.

---

## 📌 Project Overview

The Fish Waste Recycle System provides a platform where:

* **Sellers** can create and manage fish waste listings.
* **Companies** can create requirements for fish waste.
* **Companies** can place orders for available waste.
* **Sellers** can accept orders.
* The system automatically manages listing and requirement quantities.
* Users are authenticated using **JWT Authentication**.
* Access to APIs is controlled using **Role-Based Authorization**.
* Dashboard APIs provide role-specific statistics.

---

# 🛠️ Technologies Used

### Backend

* Java
* Spring Boot
* Spring MVC
* Spring Data JPA
* Hibernate
* Spring Security
* JWT Authentication
* Bean Validation
* Lombok
* ModelMapper

### Database

* MySQL

### API Testing

* Postman

### Version Control

* Git
* GitHub

---

# 👥 User Roles

The system contains three main roles:

### 1. SELLER

Seller can:

* Create waste listings
* View waste listings
* Update waste listings
* Delete waste listings
* View orders
* Accept orders
* Manage seller dashboard

### 2. COMPANY

Company can:

* Create requirements
* View requirements
* Update requirements
* Delete requirements
* View waste listings
* Create orders
* View company orders
* Manage company dashboard

### 3. ADMIN

Admin can:

* Access administrative operations
* View system-level dashboard
* View users, listings, requirements and orders according to authorization rules

---

# 🔐 Authentication & Authorization

The application uses **JWT-based authentication**.

## Authentication Flow

```text
User Login
    ↓
Email + Password
    ↓
AuthService
    ↓
Password Verification
    ↓
JWT Token Generated
    ↓
Token Returned to Client
    ↓
Client sends Bearer Token
    ↓
JwtAuthenticationFilter
    ↓
Token Validation
    ↓
SecurityContext
    ↓
Role-Based Authorization
    ↓
Access REST API
```

JWT token is sent in the request header:

```text
Authorization: Bearer <JWT_TOKEN>
```

---

# 🔑 Role-Based Authorization

API access is controlled based on the user's role.

Example:

```text
SELLER
  ↓
Waste Listing APIs
  ↓
Seller Order Operations

COMPANY
  ↓
Requirement APIs
  ↓
Create Order

ADMIN
  ↓
Administrative APIs
```

Unauthorized users receive:

```text
403 Forbidden
```

---

# 📦 Main Modules

```text
Fish Waste Recycle System
│
├── Authentication
│   ├── Login
│   ├── JWT Generation
│   └── JWT Validation
│
├── User Management
│   ├── User CRUD
│   └── Role Management
│
├── Seller Management
│   ├── Seller Profile
│   └── Seller Dashboard
│
├── Company Management
│   ├── Company Profile
│   └── Company Dashboard
│
├── Waste Listing
│   ├── Create Listing
│   ├── View Listing
│   ├── Update Listing
│   └── Delete Listing
│
├── Requirement
│   ├── Create Requirement
│   ├── View Requirement
│   ├── Update Requirement
│   └── Delete Requirement
│
├── Order Management
│   ├── Create Order
│   ├── View Order
│   ├── Update Order
│   ├── Accept Order
│   └── Cancel Order
│
├── Dashboard
│   ├── Seller Dashboard
│   ├── Company Dashboard
│   └── Admin Dashboard
│
├── Validation
│
└── Exception Handling
```

---

# 🔄 Complete Project Flow

```text
                    ┌───────────────┐
                    │     USER      │
                    └───────┬───────┘
                            │
                         Login
                            │
                            ▼
                    ┌───────────────┐
                    │ JWT Token     │
                    └───────┬───────┘
                            │
                    Role Identification
                            │
             ┌──────────────┼──────────────┐
             ▼              ▼              ▼
         SELLER          COMPANY         ADMIN
             │              │              │
             ▼              ▼              ▼
     Waste Listing      Requirement     Admin APIs
             │              │
             │              │
             └───────┬──────┘
                     │
                     ▼
                  ORDER
                     │
                     ▼
              Order = PENDING
                     │
                     ▼
              Seller Accepts
                     │
                     ▼
              Order = ACCEPTED
                     │
             ┌───────┴────────┐
             ▼                ▼
      Listing Quantity   Requirement Quantity
           Updated             Updated
```

---

# ♻️ Waste Listing Flow

Seller creates a waste listing with information such as:

* Fish type
* Waste category
* Quantity
* Price per KG
* Pickup location
* Available date
* Description

Example:

```text
Seller
  ↓
Create Waste Listing
  ↓
Listing Status = AVAILABLE
  ↓
Company views listing
  ↓
Company places order
```

When available quantity becomes zero:

```text
Quantity = 0
    ↓
Status = SOLD
```

---

# 📋 Requirement Flow

Company creates a requirement containing:

* Waste type
* Required quantity
* Budget
* Location
* Required before date
* Description

Example:

```text
Company
   ↓
Create Requirement
   ↓
Status = OPEN
   ↓
Company places orders
   ↓
Required quantity decreases
   ↓
Quantity = 0
   ↓
Status = FULFILLED
```

---

# 🛒 Order Flow

The order process is:

```text
Company
   ↓
Select Waste Listing
   ↓
Enter Quantity
   ↓
Create Order
   ↓
Order = PENDING
   ↓
Seller Accepts
   ↓
Order = ACCEPTED
```

The order calculates total amount using:

```text
Total Amount = Price Per KG × Order Quantity
```

Example:

```text
Price = ₹120/KG
Quantity = 5 KG

Total Amount = ₹120 × 5
             = ₹600
```

---

# 📊 Quantity Management

When an order is accepted, the system updates the related quantities.

Example:

```text
Before Order

Listing Quantity       = 25 KG
Requirement Quantity   = 500 KG
Order Quantity         = 5 KG
```

After order:

```text
Listing Quantity       = 20 KG
Requirement Quantity   = 495 KG
```

If listing quantity becomes zero:

```text
Listing Status = SOLD
```

If requirement quantity becomes zero:

```text
Requirement Status = FULFILLED
```

---

# ❌ Cancel Order Flow

An order can be cancelled.

```text
Order
  ↓
Cancel Order
  ↓
Order Status = CANCELLED
```

The system restores the related quantities:

```text
Listing Quantity       ↑
Requirement Quantity   ↑
Seller Available Waste ↑
```

This maintains consistency between the order, listing and requirement.

---

# 📊 Dashboard Module

The system provides separate dashboards.

## Seller Dashboard

Provides seller-level information such as:

```text
Available Fish Waste
Available Listings
Reserved Listings
Sold Listings
Total Listings
Total Orders
```

API:

```text
GET /api/dashboard/seller/{sellerId}
```

---

## Company Dashboard

Provides company-level statistics:

```text
Total Requirements
Open Requirements
Fulfilled Requirements
Total Orders
Pending Orders
Completed Orders
Cancelled Orders
Total Purchased KG
Total Spent Amount
```

API:

```text
GET /api/dashboard/company/{companyId}
```

---

## Admin Dashboard

Provides overall system statistics:

```text
Total Sellers
Total Companies
Total Listings
Available Listings
Reserved Listings
Sold Listings
Total Requirements
Total Orders
```

API:

```text
GET /api/dashboard/admin
```

---

# 🧩 Layered Architecture

The project follows a layered Spring Boot architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Entity
    ↓
MySQL
```

### Controller Layer

Handles HTTP requests and responses.

### Service Layer

Contains business logic.

### Repository Layer

Communicates with the database using Spring Data JPA.

### Entity Layer

Represents database tables.

### DTO Layer

Transfers data between client and server.

---

# 🗃️ Main Database Tables

The application contains the following main tables:

```text
user
seller
company
waste_listing
requirements
orders
```

### Relationships

```text
User
 ├── Seller
 └── Company

Seller
   │
   └── Waste Listing
           │
           └── Order

Company
   │
   ├── Requirement
   │
   └── Order

Order
 ├── Waste Listing
 └── Requirement
```

---

# ✅ Validation

The project uses Jakarta Bean Validation.

Validation is applied to request DTOs.

Examples include:

```text
@NotBlank
@NotNull
@Positive
@FutureOrPresent
```

Invalid data results in:

```text
400 Bad Request
```

---

# ⚠️ Exception Handling

Centralized exception handling is implemented using:

```text
@RestControllerAdvice
```

Handled exceptions include:

### Resource Not Found

```text
404 Not Found
```

### Bad Request

```text
400 Bad Request
```

### Duplicate Resource

```text
409 Conflict
```

### Validation Error

```text
400 Bad Request
```

### Unauthorized Access

```text
401 Unauthorized
```

### Forbidden Access

```text
403 Forbidden
```

---

# 🧪 Postman Testing

The APIs were tested using Postman.

Important tests performed:

```text
✅ Login
✅ JWT Authentication
✅ User CRUD
✅ Seller CRUD
✅ Company CRUD
✅ Waste Listing CRUD
✅ Requirement CRUD
✅ Order CRUD
✅ Order Creation
✅ Order Acceptance
✅ Order Cancellation
✅ Quantity Update
✅ Dashboard APIs
✅ Validation Testing
✅ Exception Testing
✅ 404 Testing
✅ 403 Role Authorization
```

---

# 📌 Important Business Validations

The system prevents invalid business operations.

Example:

```text
Available Listing Quantity = 5 KG

Requested Order Quantity = 10 KG

Result:

400 Bad Request
"Ordered quantity exceeds available quantity"
```

This prevents ordering more waste than is available.

---

# 🚀 API Flow Example

Complete real-world flow:

```text
1. User Registers
       ↓
2. User Logs In
       ↓
3. JWT Token Generated
       ↓
4. Seller Creates Waste Listing
       ↓
5. Company Creates Requirement
       ↓
6. Company Views Available Listings
       ↓
7. Company Creates Order
       ↓
8. Order Status = PENDING
       ↓
9. Seller Accepts Order
       ↓
10. Order Status = ACCEPTED
       ↓
11. Listing Quantity Updated
       ↓
12. Requirement Quantity Updated
       ↓
13. Seller/Company Dashboard Updated
```

---

# 🧪 Example Order

```text
Waste Listing:

Price = ₹120/KG
Available Quantity = 25 KG

Company orders:

Quantity = 5 KG
```

The system calculates:

```text
Total Amount = ₹120 × 5
             = ₹600
```

Order:

```text
Status = PENDING
```

After seller acceptance:

```text
Status = ACCEPTED
```

Listing:

```text
25 KG → 20 KG
```

Requirement:

```text
500 KG → 495 KG
```

---

# 🔒 Security Flow

```text
Client
  ↓
Authorization Header
  ↓
Bearer JWT
  ↓
JwtAuthenticationFilter
  ↓
Extract Email
  ↓
Load User
  ↓
Validate JWT
  ↓
Create Authentication
  ↓
SecurityContext
  ↓
Check User Role
  ↓
Allow / Reject Request
```

---

# 📁 Project Structure

```text
src/main/java
│
└── com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1
    │
    ├── controller
    │
    ├── dto
    │
    ├── entity
    │
    ├── enums
    │
    ├── exception
    │
    ├── repository
    │
    ├── security
    │
    └── service
        └── impl
```

---

# ▶️ How to Run the Project

### 1. Clone the repository

```bash
git clone https://github.com/sandhyarani-patil/Fish-Waste-Recycle-System.git
```

### 2. Open the project

Open the project in:

```text
IntelliJ IDEA
```

### 3. Configure MySQL

Create the required MySQL database and configure the database credentials in:

```text
application.properties
```

### 4. Run the Spring Boot application

Run the main Spring Boot application.

The API will be available at:

```text
http://localhost:8080
```

### 5. Test APIs

Use:

```text
Postman
```

to test authentication, CRUD, order, dashboard and authorization APIs.

---

# 🎯 Key Features

* RESTful API architecture
* JWT Authentication
* Role-Based Authorization
* Seller Management
* Company Management
* Waste Listing Management
* Requirement Management
* Order Management
* Order Acceptance
* Order Cancellation
* Automatic Quantity Management
* Dashboard APIs
* DTO-based communication
* JPA/Hibernate persistence
* MySQL database
* Validation
* Global Exception Handling
* Postman API Testing

---

# 📌 Project Status

```text
Backend Development       ✅ Completed
Database                  ✅ Completed
Authentication            ✅ Completed
Authorization             ✅ Completed
CRUD APIs                 ✅ Completed
Business Logic            ✅ Completed
Order Management          ✅ Completed
Dashboard APIs            ✅ Completed
Validation                ✅ Completed
Exception Handling        ✅ Completed
Postman Testing           ✅ Completed
GitHub                    ✅ Completed
React Frontend            ⏳ Optional / Planned
```

---

# 👩‍💻 Project

**Fish Waste Recycle System**

A backend REST API application developed using **Java, Spring Boot, Spring Security, JWT, Spring Data JPA, Hibernate and MySQL**.

The project demonstrates authentication, authorization, CRUD operations, business logic, database relationships, exception handling, validation and REST API development.
