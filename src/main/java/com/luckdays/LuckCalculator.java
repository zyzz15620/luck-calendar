package com.luckdays;

public class LuckCalculator {
    final static String[] statusArray = {"Đại An", "Lưu Niên", "Tốc Hỉ", "Xích Khẩu", "Tiểu Cát", "Không Vong"};
    public static int overallLuckScoreCalculator(int day, int month, int hour) {
        int monthLuckScore = luckScore(getMonthStatus(month));
        int dayLuckScore = luckScore(getDayStatus(day, month));
        int timeLuckScore = luckScore(getTimeStatus(day, month, hour));
        return monthLuckScore + dayLuckScore + timeLuckScore;
    }
    private static int luckScore(String status) {
        if (status.equals("Đại An") || status.equals("Tốc Hỉ") || status.equals("Tiểu Cát")) {
            return 1;
        } else {
            return -1;
        }
    }
    private static String getMonthStatus(int month) {
        return statusArray[(month - 1) % 6];
    }
    private static String getDayStatus(int day, int month) {
        int dayIndex = (month - 1 + (day - 1)) % 6;
        return statusArray[dayIndex];
    }
    private static String getTimeStatus(int day, int month, int hour) {
        int dayIndex = (month - 1 + (day - 1)) % 6;
        int hourFrames = ((hour + 1) / 2) % 12;
        int hourIndex = (dayIndex + hourFrames) % 6;
        return statusArray[hourIndex];
    }
    public static void main(String[] args) {
        printWeeklyLuck(1, 9);
    }
    public static String getLuckColor(int overallScore) {
        if (overallScore > 2) {
            return "Green";
        } else if (overallScore == 2) {
            return "Yellow";
        } else {
            return "Red";
        }
    }
    public static String getDetailedLuckInfo(int day, int month, int hour) {
        String monthStatus = getMonthStatus(month);
        String dayStatus = getDayStatus(day, month);
        String timeStatus = getTimeStatus(day, month, hour);
        int overallScore = overallLuckScoreCalculator(day, month, hour);
        String luckColor = getLuckColor(overallScore);
        return "Month Status: " + monthStatus +
                ", Day Status: " + dayStatus +
                ", Time Status: " + timeStatus +
                ", Overall Score: " + overallScore +
                ", Luck Color: " + luckColor;
    }
    public static String printWeeklyLuck(int startDay, int month) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            int currentDay = startDay + i;
            for (int hour = 0; hour < 24; hour += 2) {
                result.append("Day: ").append(currentDay).append(" Hour: ").append(hour).append("\n");
                result.append(getDetailedLuckInfo(currentDay, month, hour)).append("\n");
            }
        }
        return result.toString();
    }

}
