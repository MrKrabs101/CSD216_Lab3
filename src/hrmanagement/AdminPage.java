package hrmanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminPage {
    public void start(Stage stage) {
        TableView<String> adminTable = new TableView<>();
        TableColumn<String, String> nameCol = new TableColumn<>("Admin Name");
        adminTable.getColumns().add(nameCol);

        ObservableList<String> dummyData = FXCollections.observableArrayList("Admin1", "Admin2");
        adminTable.setItems(dummyData);

        Button createBtn = new Button("Create");
        Button updateBtn = new Button("Update");
        Button deleteBtn = new Button("Delete");
        Button viewBtn = new Button("View");
        Button backBtn = new Button("Back");
        Button logoutBtn = new Button("Logout");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(adminTable, createBtn, updateBtn, deleteBtn, viewBtn, backBtn, logoutBtn);

        backBtn.setOnAction(e -> {
            DashboardPage dashboard = new DashboardPage();
            dashboard.start(stage, "Ashutosh");
        });

        logoutBtn.setOnAction(e -> {
            LoginPage loginPage = new LoginPage();
            loginPage.start(stage);
        });

        Scene scene = new Scene(layout, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Admin Page");
        stage.show();
    }
}
