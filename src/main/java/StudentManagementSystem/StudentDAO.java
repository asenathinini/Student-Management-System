/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudentManagementSystem;

import Student.UndergraduateStudent;
import Student.GraduateStudent;
import Student.PartTimeStudent;
import Student.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

public class StudentDAO {

    public static void saveStudent(Student student, String passwordHash) {

        String sql = """
            INSERT INTO students
            (student_id, name, email, password_hash, department, student_type,
             credit_hours, scholarship, research_assistant, stipend)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Common fields
            stmt.setString(1, student.getStudentId());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getEmail());
            stmt.setString(5, student.getDepartment());
            stmt.setString(6, student.getStudentType());

            // Polymorphic handling
            if (student instanceof UndergraduateStudent u) {
                stmt.setInt(7, u.getCreditHours());
                stmt.setDouble(8, u.getScholarshipAmount());
                stmt.setNull(9, Types.BOOLEAN);
                stmt.setNull(10, Types.DOUBLE);
            }

            if (student instanceof GraduateStudent g) {
                stmt.setNull(7, Types.INTEGER);
                stmt.setNull(8, Types.DOUBLE);
                stmt.setBoolean(9, g.isResearchAssistant());
                stmt.setDouble(10, g.getStipend());
            } 
            
            else if (student instanceof PartTimeStudent) {
                PartTimeStudent p = (PartTimeStudent) student;

                stmt.setNull(7, Types.INTEGER);      // credit_hours
                stmt.setNull(8, Types.DOUBLE);       // scholarship
                stmt.setNull(9, Types.BOOLEAN);      // research_assistant
                stmt.setNull(10, Types.DOUBLE);      // stipend

                stmt.setInt(11, p.getHoursPerWeek());
                stmt.setDouble(12, p.getHourlyRate());
            }

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getStudentDetails(String email) {

        StringBuilder sb = new StringBuilder();

        String sql = "SELECT * FROM students WHERE email = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                sb.append("Student ID: ").append(rs.getString("student_id")).append("\n");
                sb.append("Name: ").append(rs.getString("name")).append("\n");
                sb.append("Email: ").append(rs.getString("email")).append("\n");
                sb.append("Department: ").append(rs.getString("department")).append("\n");
                sb.append("Student Type: ").append(rs.getString("student_type")).append("\n");

                String type = rs.getString("student_type");

                if ("Undergraduate Student".equals(type)) {
                    sb.append("Credit Hours: ")
                            .append(rs.getInt("credit_hours")).append("\n");
                    sb.append("Scholarship: ")
                            .append(rs.getDouble("scholarship")).append("\n");
                }

                if ("Graduate Student".equals(type)) {
                    sb.append("Research Assistant: ")
                            .append(rs.getBoolean("research_assistant")).append("\n");
                    sb.append("Stipend: ")
                            .append(rs.getDouble("stipend")).append("\n");

                } else if ("Part Time Student".equals(type)) {

                    sb.append("Hours Per Week: ")
                            .append(rs.getInt("hours_per_week")).append("\n");

                    sb.append("Hourly Rate: ")
                            .append(rs.getDouble("hourly_rate")).append("\n");
                }

            } else {
                sb.append("No student record found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static boolean studentExists(String email) {

        String sql = "SELECT 1 FROM students WHERE email = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static Student getStudentByEmail(String email) {

    String sql = "SELECT * FROM students WHERE email = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {

            String type = rs.getString("student_type");

            if ("Undergraduate Student".equals(type)) {

                return new UndergraduateStudent.Builder()
                        .studentId(rs.getString("student_id"))
                        .name(rs.getString("name"))
                        .email(rs.getString("email"))
                        .department(rs.getString("department"))
                        .creditHours(rs.getInt("credit_hours"))
                        .scholarshipAmount(rs.getDouble("scholarship"))
                        .build();
            }

            else if ("Graduate Student".equals(type)) {

                return new GraduateStudent.Builder()
                        .studentId(rs.getString("student_id"))
                        .name(rs.getString("name"))
                        .email(rs.getString("email"))
                        .department(rs.getString("department"))
                        .researchAssistant(rs.getBoolean("research_assistant"))
                        .stipend(rs.getDouble("stipend"))
                        .build();
            }

            else if ("Part Time Student".equals(type)) {

                return new PartTimeStudent.Builder()
                        .studentId(rs.getString("student_id"))
                        .name(rs.getString("name"))
                        .email(rs.getString("email"))
                        .department(rs.getString("department"))
                        .hoursPerWeek(rs.getInt("hours_per_week"))
                        .hourlyRate(rs.getDouble("hourly_rate"))
                        .build();
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}


}
