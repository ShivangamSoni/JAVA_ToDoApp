/**
 * @Project ToDoApp
 * @Class DatabaseHandler
 * *
 * @author Shivangam_Soni
 * @since 20 Jan 2020 : 1:19 PM
 */
package Shivi.Database;

import Shivi.Model.Task;
import Shivi.Model.User;
import com.mysql.cj.jdbc.ClientPreparedStatement;

import java.sql.*;

public class DatabaseHandler extends Configs {
    ClientPreparedStatement preparedStatement;
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPassword);

        return dbConnection;
    }

//Signup User
    public void signupUser(User user){
        String insert = "INSERT INTO "+Const.USERS_TABLE+"("+Const.USERS_FIRSTNAME+
                ", "+Const.USERS_LASTNAME+", "+Const.USERS_USERNAME+
                ", "+Const.USERS_PASSWORD+", "+Const.USERS_LOCATION+
                ", "+Const.USERS_GENDER+")"+"VALUES(?,?,?,?,?,?)";

    try {
        preparedStatement = (ClientPreparedStatement) getDbConnection().prepareStatement(insert);

        preparedStatement.setString(1, user.getFirstname());
        preparedStatement.setString(2, user.getLastname());
        preparedStatement.setString(3, user.getUsername());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setString(5, user.getLocation());
        preparedStatement.setString(6, user.getGender());

        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
}
//Check User for Login
    public ResultSet userLoginCheck(User user){
        ResultSet result = null;

        if(!user.getUsername().isBlank() && !user.getPassword().isBlank()){
            String checkUser = "SELECT * FROM "+Const.USERS_TABLE+
                    " WHERE "+Const.USERS_USERNAME+"= ? AND "
                    +Const.USERS_PASSWORD+"= ?";

            try {
                preparedStatement = (ClientPreparedStatement) getDbConnection().prepareStatement(checkUser);

                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());

                result = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return result;
}

//Add Task
    public void insertTask(Task task){
        String insert = "INSERT INTO "+Const.TASKS_TABLE+"("+Const.USER_ID+", "
                +Const.TASKS_TASK+ ", "+Const.TASKS_DATE+", "+Const.TASKS_DESCRIPTION+
                ")"+"VALUES(?,?,?,?)";

        try {
            preparedStatement = (ClientPreparedStatement) getDbConnection().prepareStatement(insert);

            preparedStatement.setInt(1,task.getUserId());
            preparedStatement.setString(2, task.getTask());
            preparedStatement.setTimestamp(3, task.getDatecreated());
            preparedStatement.setString(4, task.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//Getting Tasks Number
    public int getAllTaskNum(int userid){
        ResultSet result = null;
        String getAllTasks = "SELECT COUNT(*) FROM "+Const.TASKS_TABLE+
                " WHERE "+Const.USER_ID+" = ?";

        try {
            preparedStatement = (ClientPreparedStatement) getDbConnection().prepareStatement(getAllTasks);
            preparedStatement.setInt(1, userid);
            result = preparedStatement.executeQuery();

            while(result.next()){
                return result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

//Getting Complete Tasks
    public ResultSet getTasksByUser(int userid){
        ResultSet resultTasks = null;

        String query = "SELECT * FROM "+Const.TASKS_TABLE+
                " WHERE "+Const.USER_ID+"= ?";

        try{
            preparedStatement = (ClientPreparedStatement) getDbConnection().prepareStatement(query);
            preparedStatement.setInt(1,userid);
            resultTasks = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultTasks;
    }

//Delete Tasks
    public void deleteTask(int userid, int taskid){
        String query = "DELETE FROM "+Const.TASKS_TABLE+
                " WHERE "+Const.USER_ID+"=? AND "+Const.TASKS_ID+"=?";

        try{
            preparedStatement = (ClientPreparedStatement) getDbConnection().prepareStatement(query);
            preparedStatement.setInt(1, userid);
            preparedStatement.setInt(2, taskid);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//Update Task
    public void updateTask(Timestamp datecreated, String description, String task, int taskId){
        String query = "UPDATE "+Const.TASKS_TABLE+" SET "+Const.TASKS_DATE+"=?, "
                +Const.TASKS_DESCRIPTION+"=?, "+Const.TASKS_TASK+"=? WHERE "+Const.TASKS_ID+"=?";

        try{
            preparedStatement = (ClientPreparedStatement) getDbConnection().prepareStatement(query);

            preparedStatement.setTimestamp(1, datecreated);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3,task);
            preparedStatement.setInt(4, taskId);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}