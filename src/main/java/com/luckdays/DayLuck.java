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
        this.formattedDate = formatLocalDate(day, month, year);
        this.timeLuckList = generateTimeLuckList();
    }

    public void setLunarDay(int lunarDay) {
        this.lunarDay = lunarDay;
    }

    public void setLunarMonth(int lunarMonth) {
        this.lunarMonth = lunarMonth;
    }

    private String formatLocalDate(int day, int month, int year) {
        LocalDate date = LocalDate.of(year, month, day);
        return date.format(DateTimeFormatter.ofPattern("dd/MM"));
    }

    private List<TimeLuck> generateTimeLuckList() {
        List<TimeLuck> timeLuckList = new ArrayList<>();
        int[] hours = {23, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21}; // Start hours

        for (int startHour : hours) {
            int endHour = (startHour + 2) % 24;
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