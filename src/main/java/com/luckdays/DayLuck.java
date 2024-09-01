package com.luckdays;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DayLuck {
    private final int day;
    private final int month;
    private final int year;
    private int lunarDay;
    private int lunarMonth;
    private final String dayOfWeek;
    private final String formattedDate;
    private final List<TimeLuck> timeLuckList;
    public DayLuck(int day, int month, int year, int lunarDay, int lunarMonth, String dayOfWeek) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.lunarDay = lunarDay;
        this.lunarMonth = lunarMonth;
        this.dayOfWeek = dayOfWeek;
        LocalDate date = LocalDate.of(year, month, day);
        this.formattedDate = date.format(DateTimeFormatter.ofPattern("dd/MM"));
        this.timeLuckList = generateTimeLuckList();
    }

    public void setLunarDay(int lunarDay) {
        this.lunarDay = lunarDay;
    }

    public void setLunarMonth(int lunarMonth) {
        this.lunarMonth = lunarMonth;
    }

    private List<TimeLuck> generateTimeLuckList() {
        List<TimeLuck> timeLuckList = new ArrayList<>();
        int[] hours = {23, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21}; // Giờ bắt đầu

        for (int i = 0; i < hours.length; i++) {
            int startHour = hours[i];
            int endHour = hours[(i + 1) % hours.length];
            // Tính toán dựa trên ngày âm lịch
            int overallScore = LuckCalculator.overallLuckScoreCalculator(lunarDay, lunarMonth, startHour);
            String color = LuckCalculator.getLuckColor(overallScore);
            String detailedInfo = LuckCalculator.getDetailedLuckInfo(lunarDay, lunarMonth, startHour);
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

    public int getYear() {
        return year;
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
