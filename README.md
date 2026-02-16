# Student-Management-System
Java Student Management System using OOP, JDBC, Swing GUI and SOLID principles.

Overview
This project is a Java-based Student Management System built using:
Object-Oriented Programming (OOP)
Java Swing for GUI
SOLID Principles
JDBC for database access
MySQL database
Builder Design Pattern
The system allows students stored in the database to log in and view their details and calculated tuition based on their student type.

Application Architecture
The project is organized into:
gui package : User interface components
Student package : Domain model classes
StudentManagementSystem package : Business logic and data access
This ensures separation of concerns and maintainable design.

SOLID Principles Applied
Single Responsibility Principle (SRP)
Definition: A class should have only one reason to change.
How it is applied:
StudentLogin : Responsible only for login validation.
DashboardGui : Responsible only for displaying the UI.
StudentDAO : Responsible only for database operations.
StudentDashboard : Responsible only for dashboard business logic.

Example:
StudentDAO.java
public static Student getStudentByEmail(String email)

This class does not handle GUI logic or login validation. It only handles database communication.
This ensures each class has a single responsibility.

Open/Closed Principle (OCP)
Definition: Software entities should be open for extension but closed for modification.

How it is applied:
The abstract class Student defines:
public abstract double calculateTuition();

Subclasses implement their own tuition logic:
UndergraduateStudent
GraduateStudent
PartTimeStudent
If we want to add a new student type (e.g., InternationalStudent), we do NOT modify existing classes.
We simply create a new subclass.
This extends the system without modifying existing code.

Liskov Substitution Principle (LSP)
Definition: Subclasses must be substitutable for their base class.

How it is applied:
In the dashboard:
Student s = StudentDAO.getStudentByEmail(email);
System.out.println(s.calculateTuition());


The program does not need to know if s is:
UndergraduateStudent
GraduateStudent
PartTimeStudent
All subclasses correctly override calculateTuition().
This demonstrates polymorphism and LSP compliance.

Database Design
A single students table is used with:
Common attributes (student_id, name, email, department)

Type discriminator column (student_type)
Optional fields for different student types
This allows polymorphic behavior while maintaining relational simplicity.

Technologies Used
Java 21
MySQL
Maven
JDBC
Builder Pattern
Swing GUI
SOLID Principles

How to Run
Create MySQL database
Update DBConnection credentials
Run Main.java
Login using a student stored in the database
