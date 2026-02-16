/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Student;

/**
 *
 * @author asena
 */
public class UndergraduateStudent extends Student {

    private int creditHours;
    private double scholarshipAmount;

    private UndergraduateStudent(Builder builder) {
        super(builder);
        this.creditHours = builder.creditHours;
        this.scholarshipAmount = builder.scholarshipAmount;
    }

    @Override
    public double calculateTuition() {
        double ratePerCredit = 1500;
        return (creditHours * ratePerCredit) - scholarshipAmount;
    }

    @Override
    public String getStudentType() {
        return "Undergraduate Student";
    }

    @Override
    public void displayStudentDetails() {
        System.out.println("Student Type: " + getStudentType());
        System.out.println("ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Department: " + department);
        System.out.println("Credit Hours: " + creditHours);
        System.out.println("Scholarship: " + scholarshipAmount);
        System.out.println("Tuition: " + calculateTuition());
        System.out.println("-----------------------------");
    }

    public static class Builder extends Student.Builder<Builder> {

        private int creditHours;
        private double scholarshipAmount;

        public Builder creditHours(int creditHours) {
            this.creditHours = creditHours;
            return this;
        }

        public Builder scholarshipAmount(double scholarshipAmount) {
            this.scholarshipAmount = scholarshipAmount;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public UndergraduateStudent build() {
            return new UndergraduateStudent(this);
        }
    }

    public int getCreditHours() {
        return creditHours;
    }

    public double getScholarshipAmount() {
        return scholarshipAmount;
    }

}
