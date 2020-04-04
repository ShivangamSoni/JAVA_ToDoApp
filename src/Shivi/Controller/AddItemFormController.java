/**
 * @Project ToDoApp
 * @Class AddItemFormController
 * *
 * @author Shivangam_Soni
 * @since 21 Jan 2020 : 11:56 AM
 */
package Shivi.Controller;

import Shivi.Animation.Fade;
import Shivi.Animation.Shaker;
import Shivi.Database.DatabaseHandler;
import Shivi.Model.Task;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AddItemFormController {
    private static int userId;

    private DatabaseHandler dbHandler;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane addItemFormPane;

    @FXML
    private JFXTextField taskTitle;

    @FXML
    private JFXTextField taskDescription;

    @FXML
    private JFXButton saveTaskBtn;

    @FXML
    private JFXButton todoBtn;

    @FXML
    private Label sucessLabel;

    @FXML
    void initialize() {
        dbHandler = new DatabaseHandler();

        saveTaskBtn.setOnAction(e -> {
            String title = taskTitle.getText();
            String desc = taskDescription.getText();
            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTimeInMillis());
            if(!title.isBlank() && !desc.isBlank()){
                Task task = new Task(AddItemFormController.getUserId(),title, desc, timestamp);
                dbHandler.insertTask(task);
                sucess();
            }if(title.isBlank()){
                Shaker shaker = new Shaker(taskTitle);
            }if(desc.isBlank()){
                Shaker shaker = new Shaker(taskDescription);
            }
        });

        todoBtn.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Shivi/View/list.fxml"));
            try {
                AnchorPane pane = loader.load();
                Fade fade = new Fade(pane);
                addItemFormPane.getChildren().setAll(pane);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });
    }

    public void sucess(){
        int taskNum = dbHandler.getAllTaskNum(AddItemFormController.getUserId());

        sucessLabel.setVisible(true);
        Fade fade = new Fade(sucessLabel);

        todoBtn.setText("My 2DO: "+taskNum);

        taskTitle.clear();
        taskDescription.clear();
    }

    public void setUserId(int userId) { this.userId = userId;  todoBtn.setText("My 2Do: "+dbHandler.getAllTaskNum(userId));}

    public static int getUserId() { return userId; }
}
