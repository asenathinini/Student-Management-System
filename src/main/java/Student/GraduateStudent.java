/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Student;

/**
 *
 * @author asena
 */
public class GraduateStudent extends Student {

    private boolean researchAssistant;
    private double stipend;

    private GraduateStudent(Builder builder) {
        super(builder);
        this.researchAssistant = builder.researchAssistant;
        this.stipend = builder.stipend;
    }

    @Override
    public double calculateTuition() {
        double baseTuition = 20000;
        return researchAssistant ? baseTuition - stipend : baseTuition;
    }

    @Override
    public String getStudentType() {
        return "Graduate Student";
    }

    @Override
    public void displayStudentDetails() {
        System.out.println("Student Type: " + getStudentType());
        System.out.println("ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Department: " + department);
        System.out.println("Research Assistant: " + researchAssistant);
        System.out.println("Stipend: " + stipend);
        System.out.println("Tuition: " + calculateTuition());
        System.out.println("-----------------------------");
    }

    public static class Builder extends Student.Builder<Builder> {

        private boolean researchAssistant;
        private double stipend;

        public Builder researchAssistant(boolean researchAssistant) {
            this.researchAssistant = researchAssistant;
            return this;
        }

        public Builder stipend(double stipend) {
            this.stipend = stipend;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public GraduateStudent build() {
            return new GraduateStudent(this);
        }
    }

    public boolean isResearchAssistant() {
        return researchAssistant;
    }

    public double getStipend() {
        return stipend;
    }

}
