package ca.dawsoncollege;

import java.sql.Date;

public class LogsView {
    private String username;
    private Date time;
    private String status;

    public LogsView(String username, Date time, String status) {
        this.username = username;
        this.time = time;
        this.status = status;
    }

    @Override
    public String toString(){
        return "User: " + this.username +
        "\n" + "Time of change :" + this.time +
        "\n" + "Change made: " + this.status;
    }
}
