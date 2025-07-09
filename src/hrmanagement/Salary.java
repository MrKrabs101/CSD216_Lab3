package hrmanagement;

public class Salary {
    private int salaryId;
    private int employeeId;
    private double baseSalary;
    private double bonus;
    private double deductions;

    public Salary() {}

    public Salary(int salaryId, int employeeId, double baseSalary, double bonus, double deductions) {
        this.salaryId = salaryId;
        this.employeeId = employeeId;
        this.baseSalary = baseSalary;
        this.bonus = bonus;
        this.deductions = deductions;
    }

    // Getters and Setters
}
