package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;
import dao.UserDAO;
import app.SceneLoader;
import utils.SessionManager;
public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Please fill in all fields.");
            return;
        }

        User user = UserDAO.login(email, password);
        if (user != null) {
            System.out.println(user.getRole());
            if(user.getRole().equals("Customer")){

                Stage stage = (Stage) emailField.getScene().getWindow();
                SessionManager.setUser(user);
                SceneLoader.loadScene(stage,"/view/customer_dashboard.fxml","Dashboard");
            }
            // Load dashboard scene here later based on role
        } else {
            showAlert("❌ Invalid email or password.");
        }
    }

    @FXML
    public void goToRegister() {
        Stage stage = (Stage) emailField.getScene().getWindow();

        SceneLoader.loadScene(stage, "/view/register.fxml", "Register");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
