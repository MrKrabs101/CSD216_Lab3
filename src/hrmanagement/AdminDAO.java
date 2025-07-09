package hrmanagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

    public boolean validateLogin(String email, String password) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "SELECT * FROM Admin WHERE email = ? AND password = ?")) {

            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true if match found

        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
            return false;
        }
    }

    public List<Admin> getAllAdmins() {
        List<Admin> adminList = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Admin")) {

            while (rs.next()) {
                Admin admin = new Admin(
                    rs.getInt("admin_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("role")
                );
                adminList.add(admin);
            }

        } catch (SQLException e) {
            System.out.println("Fetch error: " + e.getMessage());
        }
        return adminList;
    }

    public boolean createAdmin(Admin admin) {
    String sql = "INSERT INTO Admin (name, email, password, role) VALUES (?, ?, ?, ?)";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, admin.getName());
        stmt.setString(2, admin.getEmail());
        stmt.setString(3, admin.getPassword());
        stmt.setString(4, admin.getRole());

        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.out.println("Insert error: " + e.getMessage());
        return false;
    }
}
    public boolean updateAdmin(Admin admin) {
    String sql = "UPDATE Admin SET name=?, email=?, password=?, role=? WHERE admin_id=?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, admin.getName());
        stmt.setString(2, admin.getEmail());
        stmt.setString(3, admin.getPassword());
        stmt.setString(4, admin.getRole());
        stmt.setInt(5, admin.getAdminId());

        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.out.println("Update error: " + e.getMessage());
        return false;
    }
}
    public boolean deleteAdmin(int adminId) {
    String sql = "DELETE FROM Admin WHERE admin_id=?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, adminId);
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.out.println("Delete error: " + e.getMessage());
        return false;
    }
}
    public Admin findByEmail(String email) {
    String sql = "SELECT * FROM Admin WHERE email=?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Admin(
                rs.getInt("admin_id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("role")
            );
        }
    } catch (SQLException e) {
        System.out.println("Search error: " + e.getMessage());
    }
    return null;
}

}
