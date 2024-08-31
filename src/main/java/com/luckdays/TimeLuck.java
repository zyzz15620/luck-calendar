package com.luckdays;

public class TimeLuck {
    private int hour;
    private String detailedInfo;
    private String color;

    public TimeLuck(int hour, String detailedInfo, String color) {
        this.hour = hour;
        this.detailedInfo = detailedInfo;
        this.color = color;
    }

    public int getHour() {
        return hour;
    }

    public String getDetailedInfo() {
        return detailedInfo;
    }

    public String getColor() {
        return color;
    }
}
