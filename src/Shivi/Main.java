package Shivi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/login.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("View/addItem.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("View/list.fxml"));
        primaryStage.setTitle("2-Do");
        primaryStage.setScene(new Scene(root, 700, 320));
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/Shivi/Assets/2DoLogo.png")));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
