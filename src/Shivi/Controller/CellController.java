/**
 * @Project ToDoApp
 * @Class CellController
 * *
 * @author Shivangam_Soni
 * @since 23 Jan 2020 : 12:51 PM
 */
package Shivi.Controller;

import Shivi.Database.DatabaseHandler;
import Shivi.Model.Task;
import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Calendar;

public class CellController extends JFXListCell<Task> {
    @FXML
    private AnchorPane cellPane;

    @FXML
    private ImageView cellTaskIconView;

    @FXML
    private Label cellTaskTitle;

    @FXML
    private Label cellTaskDesc;

    @FXML
    private Label cellTaskDate;

    @FXML
    private ImageView cellDelete;

    @FXML
    private ImageView cellUpdate;

    private FXMLLoader fxmlLoader;

    private DatabaseHandler dbHandler;

    @FXML
    void initialize() {

    }

    @Override
    protected void updateItem(Task myTask, boolean empty) {
        super.updateItem(myTask, empty);
        dbHandler = new DatabaseHandler();

        if(empty || myTask==null){
            setText(null);
            setGraphic(null);
        }else{
            if(fxmlLoader == null){
                fxmlLoader = new FXMLLoader(getClass().getResource("/Shivi/View/cell.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            cellTaskTitle.setText(myTask.getTask());
            cellTaskDesc.setText(myTask.getDescription());
            cellTaskDate.setText(myTask.getDatecreated().toString());

            int taskId = myTask.getTaskId();

            //Update Task
            cellUpdate.setOnMouseClicked(e -> {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Shivi/View/updateTaskForm.fxml"));
                try{
                    loader.load();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root, 400, 300));

                UpdateTaskFormController updateTaskFormController = loader.getController();
                updateTaskFormController.setTaskField(myTask.getTask());
                updateTaskFormController.setUpdateDescriptionField(myTask.getDescription());

                updateTaskFormController.updateTaskBtn.setOnAction(event -> {
                    Calendar calendar = Calendar.getInstance();
                    java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTimeInMillis());

                    dbHandler.updateTask(timestamp, updateTaskFormController.getDescription(), updateTaskFormController.getTask(), taskId);
                    (updateTaskFormController.updateTaskBtn.getScene().getWindow()).hide();
                });
                stage.show();
            });

            //Delete Task
            cellDelete.setOnMouseClicked(event -> {
                dbHandler.deleteTask(AddItemFormController.getUserId(), taskId);
                getListView().getItems().remove(getItem());
            });

            setText(null);
            setGraphic(cellPane);
        }
    }
}
