/**
 * @Project ToDoApp
 * @Class SignUpController
 * *
 * @author Shivangam_Soni
 * @since 20 Jan 2020 : 12:22 PM
 */
package Shivi.Controller;

import Shivi.Animation.Fade;
import Shivi.Animation.Shaker;
import Shivi.Database.DatabaseHandler;
import Shivi.Model.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane signUpPane;

    @FXML
    private JFXTextField firstName;

    @FXML
    private JFXTextField lastName;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField loginPassword;

    @FXML
    private JFXCheckBox signupCheckBoxMale;

    @FXML
    private JFXCheckBox signupCheckBoxFemale;

    @FXML
    private JFXTextField userLocation;

    @FXML
    private JFXButton signupBtn;

    @FXML
    private JFXButton loginBtn;

    @FXML
    void initialize() {
        signupBtn.setOnAction(actionEvent -> {
            createUser();
        });

        loginBtn.setOnAction(event -> {
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/Shivi/View/login.fxml"));
                Fade fade = new Fade(pane);
                signUpPane.getChildren().setAll(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void createUser(){
        DatabaseHandler dbHandler = new DatabaseHandler();

        String firstname = firstName.getText();
        String lastname = lastName.getText();
        String username = this.username.getText();
        String password = loginPassword.getText();
        String location = userLocation.getText();
        String gender = "";

        if(signupCheckBoxMale.isSelected() && signupCheckBoxFemale.isSelected()){
            Shaker shaker = new Shaker(signupCheckBoxFemale, signupCheckBoxMale);
        }else if (signupCheckBoxFemale.isSelected()){
            gender = "female";
        }else if(signupCheckBoxMale.isSelected()){ gender = "male"; }

        if(firstname.isBlank() || lastname.isBlank() || username.isBlank() || password.isBlank() || location.isBlank() || gender.isBlank()){
            Shaker shaker = new Shaker(firstName, lastName);
            shaker.shake(this.username);
            shaker.shake(loginPassword);
            shaker.shake(userLocation);
        }else{
            User user = new User(firstname, lastname, username, password, location, gender);
            dbHandler.signupUser(user);

            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/Shivi/View/login.fxml"));
                Fade fade = new Fade(pane);
                signUpPane.getChildren().setAll(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
