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
        switch (status) {
            case "Đại An":
                return 3;
            case "Tốc Hỉ":
                return 2;
            case "Tiểu Cát":
            case "Lưu Niên":
                return 1;
            case "Xích Khẩu":
                return -2;
            case "Không Vong":
                return -3;
            default:
                return 0;
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

    public static String getLuckColor(int overallScore) {
        if (overallScore >= 7) {
            return "#228B22"; // Rất may mắn - Màu xanh lá đậm
        } else if (overallScore >= 4) {
            return "#3CB371"; // May mắn - Màu xanh lá
        } else if (overallScore >= 1) {
            return "#90EE90"; // Hơi may mắn - Màu xanh lá nhạt
        } else if (overallScore == 0) {
            return "#F0E68C"; // Bình thường, cân bằng - Màu vàng
        } else if (overallScore >= -3) {
            return "#FF7F7F"; // Hơi xui xẻo - Màu đỏ nhạt
        } else if (overallScore >= -6) {
            return "#DC143C"; // Xui xẻo - Màu đỏ
        } else {
            return "#8B0000"; // Rất xui xẻo - Màu đỏ đậm
        }
    }

    public static String getDetailedLuckInfo(int day, int month, int hour) {
        String monthStatus = getMonthStatus(month);
        String dayStatus = getDayStatus(day, month);
        String timeStatus = getTimeStatus(day, month, hour);
        int overallScore = overallLuckScoreCalculator(day, month, hour);
        String luckColor = getLuckColor(overallScore);
        return "Tháng: " + monthStatus +
                "<br> Ngày: " + dayStatus +
                "<br> Giờ: " + timeStatus +
                "<br> Overall Score: " + overallScore;
//                "<br> Luck Color: " + luckColor;
    }
}
