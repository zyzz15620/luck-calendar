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
        this.colorClass = determineColorClass(color);
    }

    private String determineColorClass(String color) {
        return switch (color) {
            case "#8B0000" -> "red-dark";
            case "#DC143C" -> "red";
            case "#FF7F7F" -> "red-light";
            case "#F0E68C" -> "yellow";
            case "#90EE90" -> "green-light";
            case "#3CB371" -> "green";
            default -> "green-dark";
        };
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

    public String getColorClass() {
        return colorClass;
    }

    public String getFormattedTime() {
        return String.format("%02d:00 - %02d:00", startHour, endHour);
    }
}