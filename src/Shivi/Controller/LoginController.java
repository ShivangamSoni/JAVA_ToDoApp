package Shivi.Controller;

import Shivi.Animation.Fade;
import Shivi.Animation.Shaker;
import Shivi.Database.DatabaseHandler;
import Shivi.Model.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController {
    private int userId;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane RootPane;

    @FXML
    private JFXTextField loginUsername;

    @FXML
    private JFXPasswordField loginPassword;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private JFXButton signUpBtn;

    @FXML
    void initialize() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        loginBtn.setOnAction(event -> {
            String loginName = loginUsername.getText().trim();
            String loginPwd = loginPassword.getText().trim();
            if(!loginName.isBlank() && !loginPwd.isBlank()){
                User user = new User(loginName, loginPwd);
                ResultSet userRow = dbHandler.userLoginCheck(user);
                int counter = 0;
                try{
                    while (userRow.next()){
                        counter ++;
                        userId = userRow.getInt("userid");
                    }
                    if(counter==1){
                        try {
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/Shivi/View/addItemForm.fxml"));
                            AnchorPane pane = loader.load();
                            //setting userid for foreign key of tasks
                            AddItemFormController addItemFormController = loader.getController();
                            addItemFormController.setUserId(userId);

                            Fade fade = new Fade(pane);
                            RootPane.getChildren().setAll(pane);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if(counter==0){
                        Shaker shaker = new Shaker(loginUsername, loginPassword);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }if(loginName.isBlank()){
                Shaker shaker = new Shaker(loginUsername);
            }if(loginPwd.isBlank()){
                Shaker shaker = new Shaker(loginPassword);
            }
        });

        signUpBtn.setOnAction(event -> {
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/Shivi/View/signUp.fxml"));
                Fade fade = new Fade(pane);
                RootPane.getChildren().setAll(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
