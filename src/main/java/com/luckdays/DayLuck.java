package com.luckdays;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

public class DayLuck {
    private int day;
    private int month;
    private String dayOfWeek;
    private String formattedDate;
    private List<TimeLuck> timeLuckList;

    public DayLuck(int day, int month) {
        this.day = day;
        this.month = month;
        LocalDate date = LocalDate.of(LocalDate.now().getYear(), month, day);
        this.dayOfWeek = getDayOfWeek(date);
        this.formattedDate = date.format(DateTimeFormatter.ofPattern("dd/MM"));
        this.timeLuckList = generateTimeLuckList();
    }

    private String getDayOfWeek(LocalDate date) {
        switch (date.get(ChronoField.DAY_OF_WEEK)) {
            case 1: return "Chủ nhật";
            case 2: return "Thứ 2";
            case 3: return "Thứ 3";
            case 4: return "Thứ 4";
            case 5: return "Thứ 5";
            case 6: return "Thứ 6";
            case 7: return "Thứ 7";
            default: return "";
        }
    }

    private List<TimeLuck> generateTimeLuckList() {
        List<TimeLuck> timeLuckList = new ArrayList<>();
        int[] hours = {23, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21}; // Giờ bắt đầu

        for (int i = 0; i < hours.length; i++) {
            int startHour = hours[i];
            int endHour = (i == 0) ? 1 : hours[(i + 1) % hours.length]; // Giờ kết thúc
            int overallScore = LuckCalculator.overallLuckScoreCalculator(day, month, startHour);
            String color = LuckCalculator.getLuckColor(overallScore);
            String detailedInfo = LuckCalculator.getDetailedLuckInfo(day, month, startHour);
            timeLuckList.add(new TimeLuck(startHour, endHour, detailedInfo, color));
        }
        return timeLuckList;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public List<TimeLuck> getTimeLuckList() {
        return timeLuckList;
    }
}
