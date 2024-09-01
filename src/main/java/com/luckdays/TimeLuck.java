package com.luckdays;

public class TimeLuck {
    private int startHour;
    private int endHour;
    private String detailedInfo;
    private String color;

    public TimeLuck(int startHour, int endHour, String detailedInfo, String color) {
        this.startHour = startHour;
        this.endHour = endHour;
        this.detailedInfo = detailedInfo;
        this.color = color;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public String getDetailedInfo() {
        return detailedInfo;
    }

    public String getColor() {
        return color;
    }

    public String getFormattedTime() {
        return String.format("%02d:00 - %02d:00", startHour, endHour);
    }
}
