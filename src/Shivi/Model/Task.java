/**
 * @Project ToDoApp
 * @Class Task
 * *
 * @author Shivangam_Soni
 * @since 20 Jan 2020 : 5:50 PM
 */
package Shivi.Model;

import java.sql.Timestamp;

public class Task {
    private int taskId;
    private int userId;
    private String task;
    private String description;
    private Timestamp datecreated;

    public Task() {

    }

    public Task(int userId, String task, String description, Timestamp datecreated) {
        this.userId = userId;
        this.datecreated = datecreated;
        this.description = description;
        this.task = task;
    }

    public int getTaskId() { return taskId; }

    public void setTaskId(int taskId) { this.taskId = taskId; }

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Timestamp datecreated) {
        this.datecreated = datecreated;
    }
}
