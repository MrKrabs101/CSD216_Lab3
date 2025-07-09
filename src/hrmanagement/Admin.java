package hrmanagement;

public class Admin {
    private int adminId;
    private String name;
    private String email;
    private String password;
    private String role;

    public Admin() {}

    public Admin(int adminId, String name, String email, String password, String role) {
        this.adminId = adminId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    
}
