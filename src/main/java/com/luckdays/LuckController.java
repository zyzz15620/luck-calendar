package com.luckdays;

import com.luckdays.DayLuck;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LuckController {
    @GetMapping("/calendar")
    public String getCalendar(@RequestParam(defaultValue = "7") int days,
                              @RequestParam(defaultValue = "1") int startDay,
                              @RequestParam(defaultValue = "9") int month,
                              @RequestParam(defaultValue = "0") int offset,
                              Model model) {
        LocalDate startDate = LocalDate.of(LocalDate.now().getYear(), month, startDay).plusDays(offset * days);
        LocalDate endDate = startDate.plusDays(days - 1);

        String formattedStartDate = startDate.format(DateTimeFormatter.ofPattern("dd/MM"));
        String formattedEndDate = endDate.format(DateTimeFormatter.ofPattern("dd/MM"));

        int currentStartDay = startDate.getDayOfMonth();
        int currentMonth = startDate.getMonthValue();

        List<DayLuck> calendar = generateCalendar(days, currentStartDay, currentMonth);
        model.addAttribute("calendar", calendar);
        model.addAttribute("startDate", formattedStartDate);
        model.addAttribute("endDate", formattedEndDate);
        model.addAttribute("offset", offset);

        return "calendar";
    }

    private List<DayLuck> generateCalendar(int days, int startDay, int month) {
        List<DayLuck> calendar = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            LocalDate date = LocalDate.of(LocalDate.now().getYear(), month, startDay).plusDays(i);
            DayLuck dayLuck = new DayLuck(date.getDayOfMonth(), date.getMonthValue());
            calendar.add(dayLuck);
        }
        return calendar;
    }
}
