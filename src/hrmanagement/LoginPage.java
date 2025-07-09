package hrmanagement;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class LoginPage {

    public void start(Stage stage) {
        // Labels
        Label emailLabel = new Label("Email:");
        Label passwordLabel = new Label("Password:");

        // Input fields
        TextField emailField = new TextField();
        PasswordField passwordField = new PasswordField();

        // Buttons
        Button loginButton = new Button("Login");

        // Layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(emailLabel, 0, 0);
        grid.add(emailField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);

        // Button Logic
        loginButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();

            if (email.isEmpty() || password.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Please enter both email and password.");
                return;
            }

            AdminDAO adminDAO = new AdminDAO();
            boolean isValid = adminDAO.validateLogin(email, password);

            if (isValid) {
                DashboardPage dashboard = new DashboardPage();
                dashboard.start(stage, email); // Pass email as identifier
            } else {
                showAlert(Alert.AlertType.ERROR, "Invalid email or password.");
            }
        });

        // Scene Setup
        Scene scene = new Scene(grid, 400, 200);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }

    // Helper Method for Alerts
    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Login Status");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
