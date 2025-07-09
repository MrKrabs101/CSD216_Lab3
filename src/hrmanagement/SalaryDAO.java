package hrmanagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaryDAO {

    public boolean createSalary(Salary salary) {
        String sql = "INSERT INTO Salary (employee_id, base_salary, bonus, deductions) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, salary.getEmployeeId());
            stmt.setDouble(2, salary.getBaseSalary());
            stmt.setDouble(3, salary.getBonus());
            stmt.setDouble(4, salary.getDeductions());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Insert error: " + e.getMessage());
            return false;
        }
    }

    public List<Salary> getAllSalaries() {
        List<Salary> salaryList = new ArrayList<>();
        String sql = "SELECT * FROM Salary";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Salary sal = new Salary(
                    rs.getInt("salary_id"),
                    rs.getInt("employee_id"),
                    rs.getDouble("base_salary"),
                    rs.getDouble("bonus"),
                    rs.getDouble("deductions")
                );
                salaryList.add(sal);
            }
        } catch (SQLException e) {
            System.out.println("Fetch error: " + e.getMessage());
        }
        return salaryList;
    }

    public boolean updateSalary(Salary salary) {
        String sql = "UPDATE Salary SET base_salary=?, bonus=?, deductions=? WHERE salary_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, salary.getBaseSalary());
            stmt.setDouble(2, salary.getBonus());
            stmt.setDouble(3, salary.getDeductions());
            stmt.setInt(4, salary.getSalaryId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Update error: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteSalary(int salaryId) {
        String sql = "DELETE FROM Salary WHERE salary_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, salaryId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Delete error: " + e.getMessage());
            return false;
        }
    }

    public Salary findSalaryByEmployee(int employeeId) {
        String sql = "SELECT * FROM Salary WHERE employee_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Salary(
                    rs.getInt("salary_id"),
                    rs.getInt("employee_id"),
                    rs.getDouble("base_salary"),
                    rs.getDouble("bonus"),
                    rs.getDouble("deductions")
                );
            }
        } catch (SQLException e) {
            System.out.println("Search error: " + e.getMessage());
        }
        return null;
    }
}
