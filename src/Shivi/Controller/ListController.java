/**
 * @Project ToDoApp
 * @Class ListController
 * *
 * @author Shivangam_Soni
 * @since 21 Jan 2020 : 3:39 PM
 */
package Shivi.Controller;

import Shivi.Animation.Fade;
import Shivi.Animation.Shaker;
import Shivi.Database.DatabaseHandler;
import Shivi.Model.Task;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;


public class ListController {
    @FXML
    private AnchorPane listPane;

    @FXML
    private JFXListView<Task> listTasks;

    @FXML
    private JFXTextField taskTitleField;

    @FXML
    private JFXTextField taskDescriptionField;

    @FXML
    private JFXButton saveTaskBtn;

    @FXML
    private Label successLabel;

    @FXML
    private ImageView listRefresh;

    private ObservableList<Task> tasks;
    private ObservableList<Task> refreshedTasks;

    private DatabaseHandler dbHandler;

    @FXML
    void initialize() throws SQLException {
        tasks = FXCollections.observableArrayList();

        dbHandler = new DatabaseHandler();
        ResultSet resultSet = dbHandler.getTasksByUser(AddItemFormController.getUserId());

        while (resultSet.next()){
            Task myTask = new Task();
            myTask.setTask(resultSet.getString("task"));
            myTask.setDescription(resultSet.getString("description"));
            myTask.setDatecreated(resultSet.getTimestamp("datecreated"));
            myTask.setTaskId(resultSet.getInt("taskid"));
            tasks.add(myTask);
        }

        listTasks.setItems(tasks);
        listTasks.setCellFactory(CellController -> new CellController());

        saveTaskBtn.setOnAction(e -> {
            addNewTask();
        });

        listRefresh.setOnMouseClicked(e -> {
            System.out.println("Clicked");
            try {
                initialize();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void addNewTask(){
            String title = taskTitleField.getText();
            String desc = taskDescriptionField.getText();
            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTimeInMillis());

            if(!title.isBlank() && !desc.isBlank()){
                Task task = new Task(AddItemFormController.getUserId(),title, desc, timestamp);
                dbHandler.insertTask(task);

                successLabel.setVisible(true);
                Fade fade = new Fade(successLabel);

                taskTitleField.clear();
                taskDescriptionField.clear();

                tasks.add(task);
            }if(title.isBlank()){
                Shaker shaker = new Shaker(taskTitleField);
            }if(desc.isBlank()){
                Shaker shaker = new Shaker(taskDescriptionField);
            }
    }
}