
Online Order

A simple online food ordering backend service built with Spring Boot and PostgreSQL.  
It models customers, restaurants, menus, carts, and order items, and comes with basic database initialization and sample data.

Tech Stack

•  Language: Java 21
•  Framework: Spring Boot 3
◦  spring-boot-starter-web
◦  spring-boot-starter-security
◦  spring-boot-starter-data-jdbc
◦  spring-boot-starter-cache (Caffeine)
•  Database: PostgreSQL
•  Build Tool: Gradle
•  Containerization: Docker, Docker Compose

Features

•  PostgreSQL schema for:
◦  Customers, carts, restaurants, menu items, order items, authorities
•  Database initialization via database-init.sql:
◦  Auto-creates tables on startup
◦  Seeds sample restaurants and menu items
•  Caffeine-based caching configured in Spring
•  Configurable via environment variables (DB URL, username, password, etc.)
•  Ready-to-containerize Spring Boot JAR via Dockerfile

Project Structure (Relevant Parts)

•  build.gradle – Gradle build configuration & dependencies
•  src/main/resources/application.yml – Spring configuration (datasource, cache, logging)
•  src/main/resources/database-init.sql – Database schema + seed data
•  src/main/resources/public/ – Static frontend assets (HTML, icons, manifest)
•  docker-compose.yml – Local PostgreSQL database setup
•  Dockerfile – Runtime image for the built Spring Boot JAR

Prerequisites

•  Java 21 (JDK)
•  Gradle (or use the included ./gradlew wrapper)
•  Docker & Docker Compose (optional, for running PostgreSQL via containers)

Configuration

Main configuration is in src/main/resources/application.yml.

Key properties (with defaults):

•  spring.datasource.url:  
  jdbc:postgresql://${DATABASE_URL:localhost}:${DATABASE_PORT:5432}/onlineorder
•  spring.datasource.username: ${DATABASE_USERNAME:postgres}
•  spring.datasource.password: ${DATABASE_PASSWORD:secret}
•  spring.sql.init.mode: ${INIT_DB:always}  
  (controls whether database-init.sql is run)
•  spring.sql.init.schema-locations: classpath:database-init.sql
•  spring.cache.caffeine.spec: expireAfterWrite=60s

You can override these via environment variables:

•  DATABASE_URL (default: localhost)
•  DATABASE_PORT (default: 5432)
•  DATABASE_USERNAME (default: postgres)
•  DATABASE_PASSWORD (default: secret)
•  INIT_DB (default: always)

Running the Database with Docker Compose

This repo includes a simple PostgreSQL setup.
bash
This will start:

•  db (PostgreSQL 15)
◦  POSTGRES_DB=onlineorder
◦  POSTGRES_PASSWORD=secret
◦  Port: 5432 → 5432

Data is persisted in the onlineorder-pg-local Docker volume.

Building and Running the Application (Locally)

1. Build the JAR
bash
   or, if you have Gradle installed:
bash
2. Run the app

   Make sure PostgreSQL is running and accessible (via Docker Compose or your own setup), then:
bash
   or run the built JAR directly:
bash
3. By default, the application will start on port 8080 (standard Spring Boot default).

Building and Running with Docker

1. Build the JAR (required before building the image)
bash
2. Build the Docker image
bash
3. Run the container

   Make sure your PostgreSQL instance is reachable from the container (e.g., via Docker network):
bash
   Replace db-host with the correct host name (e.g., a Docker network service name or host.docker.internal depending on your setup).

Database Schema Overview

The initialized schema includes:

•  customers
•  carts
•  restaurants
•  menu_items
•  order_items
•  authorities

database-init.sql:

•  Drops existing tables (if any)
•  Creates tables with proper FK constraints
•  Inserts sample restaurants and menu items
