package hrmanagement;

public class Employee {
    private int employeeId;
    private String name;
    private String email;
    private String department;
    private String position;
    private String role;

    public Employee() {}

    public Employee(int employeeId, String name, String email, String department, String position, String role) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.department = department;
        this.position = position;
        this.role = role;
    }

    // Getters and Setters
}
