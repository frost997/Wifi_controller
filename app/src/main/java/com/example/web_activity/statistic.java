package com.example.web_activity;

public class statistic {
    private String Name;
    private String Temperature ;
    private String Water_level;
    private String GyroX ;


    private String GyroY ;
    private String GyroZ ;
    private String time_stamp ;
    public statistic(String Name, String Temperature, String Water_level, String time_stamp) {

        this.Name = Name;
        this.Temperature = Temperature;
        this.Water_level = Water_level;
        this.time_stamp = time_stamp;
    }
    public String getName() {
        return Name;
    }

    public String getTemp() {
        return Temperature;
    }
    public String getWater_level() {
        return Water_level;
    }

    public String getGyroX() {
        return GyroX;
    }

    public String getGyroY() {
        return GyroY;
    }
    public String getGyroZ() {
        return GyroZ;
    }
    public String getTime_stamp() {
        return time_stamp;
    }

}
