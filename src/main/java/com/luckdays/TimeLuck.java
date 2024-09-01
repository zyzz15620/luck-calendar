package com.luckdays;

public class TimeLuck {
    private int startHour;
    private int endHour;
    private String detailedInfo;
    private String color;
    private String colorClass;

    public TimeLuck(int startHour, int endHour, String detailedInfo, String color) {
        this.startHour = startHour;
        this.endHour = endHour;
        this.detailedInfo = detailedInfo;
        this.color = color;
        this.colorClass = getColorClass(); // Tạo class dựa trên màu
    }
    public String getColorClass() {
        if (this.color.equals("#8B0000")) {
            return "red-dark";
        } else if (this.color.equals("#DC143C")) {
            return "red";
        } else if (this.color.equals("#FF7F7F")) {
            return "red-light";
        } else if (this.color.equals("#F0E68C")) {
            return "yellow";
        } else if (this.color.equals("#90EE90")) {
            return "green-light";
        } else if (this.color.equals("#3CB371")) {
            return "green";
        } else {
            return "green-dark";
        }
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
