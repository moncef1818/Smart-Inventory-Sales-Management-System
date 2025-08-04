package controller;

import app.SceneLoader;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.User;
import utils.SessionManager;

public class CustomerDashboardController {
    @FXML private Label welcomeLabel;
    @FXML private StackPane contentArea;
    @FXML private Button logoutButton;

    // Handle user session (to be set from LoginController)
    private static User currentUser=SessionManager.getUser();


    @FXML
    public void initialize() {
        if (currentUser != null) {
            welcomeLabel.setText("Welcome, " + currentUser.getUsername() + "!");
        }
    }

    // Navigation actions
    @FXML
    private void showProducts() {
        // Load a VBox of product cards here
    }

    @FXML
    private void showCart() {
        // Show cart table or list
    }

    @FXML
    private void showOrders() {
        // Load previous orders
    }

    @FXML
    private void handleLogout() {
        SceneLoader.loadScene((Stage) logoutButton.getScene().getWindow(), "/view/login.fxml", "Login");
    }

}
