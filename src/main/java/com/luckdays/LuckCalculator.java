package com.luckdays;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LuckCalculator {
    final static String[] statusArray = {"Đại An", "Lưu Niên", "Tốc Hỉ", "Xích Khẩu", "Tiểu Cát", "Không Vong"};

    public static double overallLuckScoreCalculator(int day, int month, int hour) {
        int monthLuckScore = luckScore(getMonthStatus(month));
        int dayLuckScore = luckScore(getDayStatus(day, month));
        int timeLuckScore = luckScore(getTimeStatus(day, month, hour));
        double result = ((monthLuckScore*0.20) + (dayLuckScore*0.35)+ (timeLuckScore*0.45))*3.33;
        return new BigDecimal(result).setScale(1, RoundingMode.HALF_UP).doubleValue();
    }

    private static int luckScore(String status) {
        return switch (status) {
            case "Đại An" -> 3;
            case "Tốc Hỉ" -> 2;
            case "Tiểu Cát", "Lưu Niên" -> 1;
            case "Xích Khẩu" -> -2;
            case "Không Vong" -> -3;
            default -> 0;
        };
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

    public static String getLuckColor(double overallScore) {
        if (overallScore >= 7.5) {
            return "#32CD32"; // Rất may mắn - Màu xanh teal
        } else if (overallScore >= 2) {
            return "#98FB98"; // May mắn - Màu xanh lá vừa
        } else if (overallScore > -2 && overallScore < 2) {
            return "#F0E68C"; // Bình thường, cân bằng - Màu vàng
        } else if (overallScore >= -6.4) {
            return "#FFB6C1"; // Xui xẻo - Màu đỏ cam
        } else {
            return "#FF6347"; // Rất xui xẻo - Màu đỏ xám
        }
    }

    public static String getDetailedLuckInfo(int day, int month, int hour) {
        String monthStatus = getMonthStatus(month);
        String dayStatus = getDayStatus(day, month);
        String timeStatus = getTimeStatus(day, month, hour);
        double overallScore = overallLuckScoreCalculator(day, month, hour);
        String luckColor = getLuckColor(overallScore);
        return "Tháng: " + monthStatus +
                "<br> Ngày: " + dayStatus +
                "<br> Giờ: " + timeStatus +
                "<br> Điểm: " + overallScore;
//                "<br> Luck Color: " + luckColor;
    }
}
