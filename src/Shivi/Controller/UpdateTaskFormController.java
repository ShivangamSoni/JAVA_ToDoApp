/**
 * @Project ToDoApp
 * @Class UpdateTaskFormController
 * *
 * @author Shivangam_Soni
 * @since 23 Jan 2020 : 3:44 PM
 */
package Shivi.Controller;

import Shivi.Animation.Fade;
import Shivi.Animation.Shaker;
import Shivi.Model.Task;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;


public class UpdateTaskFormController {
    @FXML
    private AnchorPane updateItemFormPane;

    @FXML
    private JFXTextField taskTitle;

    @FXML
    private JFXTextField taskDescription;

    @FXML
    public JFXButton updateTaskBtn;

    @FXML
    void initialize() {

    }
    public void setTaskField(String task) {
        this.taskTitle.setText(task);
    }

    public String getTask() {
        return this.taskTitle.getText().trim();
    }

    public void setUpdateDescriptionField(String description) {
        this.taskDescription.setText(description);
    }

    public String getDescription() {
        return this.taskDescription.getText().trim();
    }
}
