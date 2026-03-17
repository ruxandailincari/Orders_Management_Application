# Orders Management Application
A robust Java desktop application for managing customers, products, and orders. Built with a Layered Architecture and utilizing Reflection for dynamic database operations.

## Overview
This application provides a complete CRUD interface for a retail environment. It features a generic DAO layer that dynamically generates SQL queries using reflection, reducing code duplication and increasing maintainability.

## Key Features
- Generic DAO Pattern: AbstractDAO<T> uses reflection to automatically generate INSERT, UPDATE, DELETE, and SELECT queries for any entity
- Layered Architecture: Clear separation between Presentation, Business Logic and Data Access
- Dynamic Table Generation: Uses reflection to build JTable headers and rows dynamically from any object list
- Security:
  - PreparedStatement: Prevents SQL Injection
  - Validation: Centralized validation logic for entities (email, price, stock)

## Technologies Used
- Language: Java
- Database: MySQL
- GUI: Java Swing
- Design Pattern: Strategy
