package com.luckdays;

import java.util.ArrayList;
import java.util.List;

public class DayLuck {
    private int day;
    private int month;
    private List<TimeLuck> timeLuckList;

    public DayLuck(int day, int month) {
        this.day = day;
        this.month = month;
        this.timeLuckList = generateTimeLuckList();
    }

    private List<TimeLuck> generateTimeLuckList() {
        List<TimeLuck> timeLuckList = new ArrayList<>();
        for (int hour = 0; hour < 24; hour += 2) {
            int overallScore = LuckCalculator.overallLuckScoreCalculator(day, month, hour);
            String color = LuckCalculator.getLuckColor(overallScore);
            String detailedInfo = LuckCalculator.getDetailedLuckInfo(day, month, hour);
            timeLuckList.add(new TimeLuck(hour, detailedInfo, color));
        }
        return timeLuckList;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public List<TimeLuck> getTimeLuckList() {
        return timeLuckList;
    }
}
