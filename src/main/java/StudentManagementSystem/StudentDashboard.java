/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudentManagementSystem;

import Student.Student;
import Student.UndergraduateStudent;
import Student.GraduateStudent;
import Student.PartTimeStudent;

public class StudentDashboard {

    private Student student;

    // Constructor loads student once
    public StudentDashboard(String email) {
        this.student = StudentDAO.getStudentByEmail(email);
    }

    // Check if student exists
    public boolean studentExists() {
        return student != null;
    }

    // Basic getters
    public String getStudentId() {
        return student.getStudentId();
    }

    public String getName() {
        return student.getName();
    }

    public String getEmail() {
        return student.getEmail();
    }

    public String getDepartment() {
        return student.getDepartment();
    }

    public String getStudentType() {
        return student.getStudentType();
    }

    public double getTuition() {
        return student.calculateTuition();
    }

    // Polymorphic additional info
    public String getAdditionalDetails() {

        if (student instanceof UndergraduateStudent u) {

            return "Credit Hours: " + u.getCreditHours() +
                   "\nScholarship: R " + u.getScholarshipAmount();
        }

        else if (student instanceof GraduateStudent g) {

            return "Research Assistant: " + g.isResearchAssistant() +
                   "\nStipend: R " + g.getStipend();
        }

        else if (student instanceof PartTimeStudent p) {

            return "Hours Per Week: " + p.getHoursPerWeek() +
                   "\nHourly Rate: R " + p.getHourlyRate();
        }

        return "";
    }

    // Full formatted summary (optional)
    public String getFullSummary() {

        if (!studentExists()) {
            return "Student record not found.";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("Student ID: ").append(getStudentId()).append("\n");
        sb.append("Name: ").append(getName()).append("\n");
        sb.append("Email: ").append(getEmail()).append("\n");
        sb.append("Department: ").append(getDepartment()).append("\n");
        sb.append("Student Type: ").append(getStudentType()).append("\n");
        sb.append(getAdditionalDetails()).append("\n");
        sb.append("Tuition: R ").append(getTuition()).append("\n");

        return sb.toString();
    }
}

