# ✈️ &nbsp;Sequoia Air - SERVER Web Application

### *Connecting you with the world and the world with you.*

Sequoia Airways is a global airline, bringing people, places, and diverse cultures closer together for more than 20 years.  
This repository contains the **server-side backend application** powering flight management, user authentication, booking, and notifications.

---

## 🚀 Tech Stack
- **Backend Framework:** Spring Boot (Java)
- **Authentication & Security:** Spring Security, JWT, OAuth2 (Google Login)
- **Database:** MySQL (with JPA/Hibernate)
- **Templating & Utilities:** Thymeleaf, Lombok, ModelMapper, Jackson
- **Mailing:** JavaMailSender (SMTP with Gmail)

---

## 🔑 Authentication
Sequoia Air supports **two authentication flows**:
1. **Local Sign-In / Sign-Up** (via Spring Security & JWT)
2. **Google OAuth2 Authentication**

Users receive a **JWT token** upon successful login, enabling secure API access.

---

## 📬 Mailing System
The application includes a robust **email notification system** powered by **JavaMailSender** with Gmail SMTP.

It automatically sends emails for key events such as:
- Booking confirmations with detailed flight and passenger information.
- Welcome messages to new users after registration.

All outgoing emails are handled reliably and recorded in the database for tracking purposes, ensuring users receive timely notifications and confirmations.


---

## 📖 API Documentation
Interactive API docs available via **Swagger UI**:

---

## ⚙️ Running the Application

### 1️⃣ Prerequisites
- **Java 17+**
- **Maven**
- **MySQL**


### 2️⃣ Setup

**Clone the repository:**
```bash
git clone https://github.com/your-username/sequoia-air-server.git
cd sequoia-air-server
```


### 3️⃣ Install dependencies:

```bash
mvn clean install
```


### 4️⃣ Configure Environment

- Rename `application-dev.properties` → `application.properties`
- Update the following variables:
    - **MySQL Database credentials**
    - **Mail Sender (SMTP)**
    - **Google OAuth2 Client IDs**
    - **Frontend URLs**


### 5️⃣ Run the App

```bash
mvn spring-boot:run
```


---

## 🛠️ What the Server Does

The backend powers core airline operations including:

- **Authentication & User Management**
    - Secure registration & login with JWT
    - Google OAuth2 integration
    - Role-based access control (e.g., ADMIN, PILOT, USER)

- **Flight Operations**
    - Create, search, update, and manage flights
    - Country/City/Route data management

- **Airports**
    - Add/update airport details
    - Validate airports against countries & cities

- **Booking System**
    - Create and manage flight bookings
    - Generate booking references
    - Send e-ticket confirmation via email

- **Notifications**
    - Email system for bookings & onboarding
    - Database record of sent notifications

- **Admin Role Management**
    - Create/update user roles
    - Assign roles dynamically to users


---

## 📂 Project Structure (Simplified)

```bash
src/main/java/com/emmanuelanene/sequoia_air
│── controllers/        # REST API endpoints
│── services/           # Business logic
│── entities/           # JPA entities
│── repo/               # Database repositories
│── dtos/               # Data Transfer Objects
│── exceptions/         # Custom exceptions
│── config/             # Security & app configuration
```

---

## 🧑‍💻 Contribution

Want to contribute?

1. **Fork the repository**
2. **Create a feature branch**
3. **Submit a Pull Request (PR)**


---

## 📜 License

This project is licensed under the **MIT License** – feel free to use, modify, and distribute.
