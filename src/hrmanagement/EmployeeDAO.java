package hrmanagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public boolean createEmployee(Employee emp) {
        String sql = "INSERT INTO Employee_Detail (name, email, department, position, role) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, emp.getName());
            stmt.setString(2, emp.getEmail());
            stmt.setString(3, emp.getDepartment());
            stmt.setString(4, emp.getPosition());
            stmt.setString(5, emp.getRole());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Insert error: " + e.getMessage());
            return false;
        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> empList = new ArrayList<>();
        String sql = "SELECT * FROM Employee_Detail";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Employee emp = new Employee(
                    rs.getInt("employee_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("department"),
                    rs.getString("position"),
                    rs.getString("role")
                );
                empList.add(emp);
            }
        } catch (SQLException e) {
            System.out.println("Fetch error: " + e.getMessage());
        }
        return empList;
    }

    public boolean updateEmployee(Employee emp) {
        String sql = "UPDATE Employee_Detail SET name=?, email=?, department=?, position=?, role=? WHERE employee_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, emp.getName());
            stmt.setString(2, emp.getEmail());
            stmt.setString(3, emp.getDepartment());
            stmt.setString(4, emp.getPosition());
            stmt.setString(5, emp.getRole());
            stmt.setInt(6, emp.getEmployeeId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Update error: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteEmployee(int employeeId) {
        String sql = "DELETE FROM Employee_Detail WHERE employee_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Delete error: " + e.getMessage());
            return false;
        }
    }

    public Employee findByEmail(String email) {
        String sql = "SELECT * FROM Employee_Detail WHERE email=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Employee(
                    rs.getInt("employee_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("department"),
                    rs.getString("position"),
                    rs.getString("role")
                );
            }
        } catch (SQLException e) {
            System.out.println("Search error: " + e.getMessage());
        }
        return null;
    }
}
