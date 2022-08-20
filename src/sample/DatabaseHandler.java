package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseHandler extends  Configs {
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    //Write metods
    public void addNewUser(User user){
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_Name + "," + Const.USER_Nick
                + "," + Const.User_Time + ")" + "VALUES(?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getUserName());
            prSt.setString(2, user.getUserNick());
            prSt.setInt(3, user.getTime());
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // this method Show tables in all forms in application
    public ObservableList<Table> showUsersTables() {
        String name;
        String nick ;
        int time;

        ObservableList<Table> tableUsers = FXCollections.observableArrayList();

        String question = "select * from minecraftuserstables.user";
        try {
            Connection connection = getDbConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(question);

            while (resultSet.next()){
               name = resultSet.getString("UserName");
               nick = resultSet.getString("UserNick");
               time = resultSet.getInt("Time");
               Table table = new Table(name, nick, time);
                tableUsers.add(table);
            }
            statement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return tableUsers;
    }
    // this method add time all users if user name is Admin else method removed time in select user
    public void UpdateSessionTime(String Name, int Time){
        String selectUser = "select * from minecraftuserstables.user WHERE UserName = '" + Name + "'";
        // this string it is SQL request update DB
        String selectUpdateSQL = "UPDATE `minecraftuserstables`.`user` SET `Time` = ? WHERE `UserName` = '"+ Name +"'";
        try {
            Connection connection = getDbConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectUser);
            PreparedStatement prSt = getDbConnection().prepareStatement(selectUpdateSQL);
                while (resultSet.next()) {
                    int te = resultSet.getInt("Time");
                    if (Name.equals("Alex") || Name.equals("alex")){
                        te += Time;
                        prSt.setInt(1, te);
                        prSt.executeUpdate();
                    }
                    else {
                        te -= Time;
                        prSt.setInt(1, te);
                        prSt.executeUpdate();
                    }
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    // this method allows Edit user nick
    public void EditUser(String OldNick, String Newick){
        String select = "UPDATE `minecraftuserstables`.`user` SET `UserNick` = ? WHERE `UserNick` = '"+ OldNick +"'";
        try {
                PreparedStatement prSt = getDbConnection().prepareStatement(select);
                prSt.setString(1, Newick);
                prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    // this method allows remove input user
    public void DeleteUser(String OldNick){

        String select = "DELETE FROM `minecraftuserstables`.`user` WHERE `UserNick` = '"+ OldNick +"'";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    // this method add time everyone new players
    public int SelectTimeNewUser(){
        int time = 0;
        String request = "select * from minecraftuserstables.user WHERE UserName = 'Alex'";
        try {
            Connection connection = getDbConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);
            while (resultSet.next()){
                time = resultSet.getInt("Time");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return time;
    }

}

