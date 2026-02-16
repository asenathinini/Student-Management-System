/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Student;

/**
 *
 * @author asena
 */
public class PartTimeStudent extends Student {

    private int hoursPerWeek;
    private double hourlyRate;

    private PartTimeStudent(Builder builder) {
        super(builder);
        this.hoursPerWeek = builder.hoursPerWeek;
        this.hourlyRate = builder.hourlyRate;
    }

    @Override
    public double calculateTuition() {
        return hoursPerWeek * hourlyRate;
    }

    @Override
    public String getStudentType() {
        return "Part Time Student";
    }

    @Override
    public void displayStudentDetails() {
        System.out.println("Student Type: " + getStudentType());
        System.out.println("ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Department: " + department);
        System.out.println("Hours Per Week: " + hoursPerWeek);
        System.out.println("Hourly Rate: " + hourlyRate);
        System.out.println("Total Earnings: " + calculateTuition());
        System.out.println("-----------------------------");
    }

    public static class Builder extends Student.Builder<Builder> {

        private int hoursPerWeek;
        private double hourlyRate;

        public Builder hoursPerWeek(int hoursPerWeek) {
            this.hoursPerWeek = hoursPerWeek;
            return this;
        }

        public Builder hourlyRate(double hourlyRate) {
            this.hourlyRate = hourlyRate;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public PartTimeStudent build() {
            return new PartTimeStudent(this);
        }
    }

    public int getHoursPerWeek() {
        return hoursPerWeek;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }
}
