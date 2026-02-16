/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Student;

/**
 *
 * @author asena
 */
public abstract class Student {

    protected String studentId;
    protected String name;
    protected String email;
    protected String department;

    protected Student(Builder<?> builder) {
        this.studentId = builder.studentId;
        this.name = builder.name;
        this.email = builder.email;
        this.department = builder.department;
    }

    public abstract double calculateTuition();

    public abstract String getStudentType();

    public abstract void displayStudentDetails();

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public static abstract class Builder<T extends Builder<T>> {

        private String studentId;
        private String name;
        private String email;
        private String department;

        public T studentId(String studentId) {
            this.studentId = studentId;
            return self();
        }

        public T name(String name) {
            this.name = name;
            return self();
        }

        public T email(String email) {
            this.email = email;
            return self();
        }

        public T department(String department) {
            this.department = department;
            return self();
        }

        protected abstract T self();

        public abstract Student build();
    }

}
