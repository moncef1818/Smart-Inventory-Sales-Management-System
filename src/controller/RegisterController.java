package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;
import dao.UserDAO;
import app.SceneLoader;
import utils.PasswordUtil;

public class RegisterController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField passwordConfirmField;


    @FXML
    public void handleRegister() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = passwordConfirmField.getText();

        if (email.isEmpty() || password.isEmpty() || username.isEmpty() || confirmPassword.isEmpty()) {
            showAlert("Please fill in all fields.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert("Passwords do not match.");
            return;
        }

        String hashedPassword = PasswordUtil.hashPassword(password);

        User newUser = new User(email, username, hashedPassword, 1);  // Role 1 = normal user

        // Attempt registration
        boolean registered = UserDAO.registerUser(newUser);

        if (!registered) {
            showAlert("❌ Registration failed. Email may already be used.");
            return;
        }

        // Fresh login after registration
        User user = UserDAO.login(email, password);
        if (user != null) {
            showAlert("✅ Welcome, " + user.getUsername() + "! Role: " + user.getRole());
        } else {
            showAlert("❌ Login failed after registration.");
        }
    }

    @FXML
    public void goToLogin() {
        Stage stage = (Stage) emailField.getScene().getWindow();
        SceneLoader.loadScene(stage, "/view/login.fxml", "Login");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
