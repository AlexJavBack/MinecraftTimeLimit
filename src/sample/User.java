package sample;

public class User {
    private String userName;
    private String userNick;
    private int time;


    public User(String UserName, String UserNick, int Time) {
        this.userName = UserName;
        this.userNick = UserNick;
        this.time = Time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

}

