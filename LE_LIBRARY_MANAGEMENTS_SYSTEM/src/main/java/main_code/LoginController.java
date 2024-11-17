package main_code;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.animation.Interpolator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.io.IOException;

public class LoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button close;

    public void close() {
        System.exit(0);
    }

    @FXML
    private Button exit;

    @FXML
    private AnchorPane container;

    @FXML
    private Button staffLog;

    @FXML
    private PasswordField passwordtextfield;

    @FXML
    private TextField passwordTextVisible;

    @FXML
    private Button passButton;

    @FXML
    private ImageView passwordIcon;

    private boolean isPasswordVisible = false;

    @FXML
    private AnchorPane sidedoor;

    @FXML
    public void togglePasswordVisibility() {
        if (isPasswordVisible) {
            // Hide plain text and show PasswordField
            passwordTextVisible.setVisible(false);
            passwordtextfield.setText(passwordTextVisible.getText());
            passwordtextfield.setVisible(true);

            // Update the icon
            passwordIcon.setImage(new Image(getClass().getResourceAsStream("icons/passIconeyeshow.png")));
        } else {
            // Show plain text and hide PasswordField
            passwordtextfield.setVisible(false);
            passwordTextVisible.setText(passwordtextfield.getText());
            passwordTextVisible.setVisible(true);

            // Update the icon
            passwordIcon.setImage(new Image(getClass().getResourceAsStream("icons/passIconeyehide.png")));
        }
        isPasswordVisible = !isPasswordVisible;
    }



    private void slidePaneToRight() {
        TranslateTransition slide = new TranslateTransition(Duration.millis(500), sidedoor);
        slide.setByX(sidedoor.getWidth()); // Slide to the right
        slide.setInterpolator(Interpolator.EASE_BOTH); // Smooth animation
        slide.play();

        // Add a listener to execute actions after the slide is complete
        slide.setOnFinished(event -> {
            // Get the current stage and scene
            Stage stage = (Stage) sidedoor.getScene().getWindow();
            try {
                Parent newRoot = FXMLLoader.load(getClass().getResource("login_view.fxml"));
                Scene newScene = new Scene(newRoot);
                stage.setScene(newScene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void switchLogin(ActionEvent event) throws Exception {
        slidePaneToRight(); // Slide sidedoor to the right
    }

    private void slidePaneToLeft() {
        TranslateTransition slide = new TranslateTransition(Duration.millis(500), sidedoor);
        slide.setByX(-sidedoor.getWidth()); // Slide to the left
        slide.setInterpolator(Interpolator.EASE_BOTH);
        slide.play();

        // Add a listener to execute actions after the slide is complete
        slide.setOnFinished(event -> {
            // Get the current stage and scene
            Stage stage = (Stage) sidedoor.getScene().getWindow();
            try {
                Parent newRoot = FXMLLoader.load(getClass().getResource("staffLogin_view.fxml"));
                Scene newScene = new Scene(newRoot);
                stage.setScene(newScene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void switchStaff(ActionEvent event) throws Exception {
        slidePaneToLeft(); // Slide sidedoor to the left
    }
}