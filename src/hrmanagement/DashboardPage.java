package hrmanagement;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class DashboardPage {
    public void start(Stage stage, String username) {
        Label welcomeLabel = new Label("Welcome, " + username);
        Label dateLabel = new Label("Date: " + LocalDate.now());

        Button adminBtn = new Button("Admin");
        Button employeeBtn = new Button("Employee");
        Button logoutBtn = new Button("Logout");
        Button exitBtn = new Button("Exit");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(welcomeLabel, dateLabel, adminBtn, employeeBtn, logoutBtn, exitBtn);

        adminBtn.setOnAction(e -> {
            AdminPage adminPage = new AdminPage();
            adminPage.start(stage);
        });

        employeeBtn.setOnAction(e -> {
            EmployeePage employeePage = new EmployeePage();
            employeePage.start(stage);
        });

        logoutBtn.setOnAction(e -> {
            LoginPage loginPage = new LoginPage();
            loginPage.start(stage);
        });

        exitBtn.setOnAction(e -> stage.close());

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }
}
