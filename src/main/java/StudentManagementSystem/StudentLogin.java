/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudentManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentLogin {

    public enum LoginResult {
        SUCCESS,
        STUDENT_NOT_REGISTERED,
        WRONG_PASSWORD
    }

    public static LoginResult login(String email, String password) {

        String sql = "SELECT password_hash FROM students WHERE email = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            // Student not found
            if (!rs.next()) {
                return LoginResult.STUDENT_NOT_REGISTERED;
            }

            String storedHash = rs.getString("password_hash");
            String inputHash = PasswordUtil.hash(password);

            if (storedHash.equals(inputHash)) {
                return LoginResult.SUCCESS;
            } else {
                return LoginResult.WRONG_PASSWORD;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return LoginResult.STUDENT_NOT_REGISTERED;
    }
}
