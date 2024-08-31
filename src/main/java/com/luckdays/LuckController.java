package com.luckdays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LuckController {

    @GetMapping("/calendar")
    public String getCalendar(@RequestParam(defaultValue = "7") int days,
                              @RequestParam(defaultValue = "1") int startDay,
                              @RequestParam(defaultValue = "9") int month,
                              Model model) {
        List<DayLuck> calendar = generateCalendar(days, startDay, month);
        model.addAttribute("calendar", calendar);
        return "calendar";
    }

    private List<DayLuck> generateCalendar(int days, int startDay, int month) {
        List<DayLuck> calendar = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            int currentDay = startDay + i;
            DayLuck dayLuck = new DayLuck(currentDay, month);
            calendar.add(dayLuck);
        }
        return calendar;
    }
}
