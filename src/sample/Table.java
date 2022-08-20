package sample;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Table {
    private final SimpleStringProperty userName;
    private final SimpleStringProperty userNick;
    private final SimpleIntegerProperty time;

    public Table(String userName, String userNick, int time) {
        this.userName = new SimpleStringProperty(userName);
        this.userNick = new SimpleStringProperty(userNick);
        this.time = new SimpleIntegerProperty(time);
    }

    public String getUserName() {
        return userName.get();
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getUserNick() {
        return userNick.get();
    }

    public void setUserNick(String userNick) {
        this.userNick.set(userNick);
    }

    public int getTime() {
        return time.get();
    }

    public void setTime(int time) {
        this.time.set(time);
    }

}
