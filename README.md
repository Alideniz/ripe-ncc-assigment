# Epigram Web Application

## Project Overview

The Epigram Web Application is a platform for users to share and view epigrams. It consists of a backend service built
with Java, Spring Boot and PostgreSQL, and a frontend developed with React.js. The entire application is containerized
using Docker.

## Features

- **Java Spring Boot Backend**: Provides a robust and scalable REST API for managing epigrams.
- **PostgreSQL Database**: Stores all the data related to epigrams entities.
- **React.js Frontend**: Offers a modern and responsive user interface for interacting with the application.
- **Dockerized Deployment**: Ensures easy setup and deployment of the application across different environments.

## Prerequisites

Before running the application, ensure you have the following installed:

- Docker
- Docker Compose

## Setup and Running

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Alideniz/ripe-ncc.git
   cd ripe-ncc
   ```

2. **Build and run the application using Docker Compose:**
   ```bash
   # Navigate to the backend directory
   cd backend
   
   # Clean and package the backend application
   ./mvnw clean package
   
   # Go back to the root directory
   cd ..
   # Build and start the application using Docker Compose
   docker compose -f docker/docker-compose.yaml up  --build
   ```

3. **Access the application:**
    - The backend API will be available at `http://localhost:8080`
    - The frontend UI will be available at `http://localhost:80`

## Development

### Backend

To run the backend locally without Docker:

1. Navigate to the backend directory:
   ```bash
   cd backend
   ```

2. Install dependencies and run the application:
   ```bash
   ./mvnw clean package
   docker compose -f docker/docker-compose.yaml -p docker up -d postgres
   ./mvnw spring-boot:run
   ```

### Frontend

To run the frontend locally without Docker:

1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```

2. Install dependencies and start the development server:
   ```bash
   npm install
   npm start
   ```

## Contact

For any questions or inquiries, please contact alidenizaltun33@gmail.com