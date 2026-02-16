/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudentManagementSystem;
/**
 *
 * @author asena
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBSetup {

    
    public static void createDatabase() {

                try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Create database if it doesn't exist
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS studentdb");
            stmt.executeUpdate("USE studentdb");

            // Create students table
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS students (
                    student_id VARCHAR(20) PRIMARY KEY,
                    name VARCHAR(100),
                    email VARCHAR(100) UNIQUE,
                    password_hash VARCHAR(255),
                    department VARCHAR(50),
                    student_type VARCHAR(30),

                    credit_hours INT,
                    scholarship DOUBLE,

                    research_assistant BOOLEAN,
                    stipend DOUBLE,
                               
                    hours_per_week INT,
                    hourly_rate DOUBLE          
                )
            """);

            System.out.println("Database and tables ready!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

    

