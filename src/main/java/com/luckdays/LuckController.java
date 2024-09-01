package com.luckdays;

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
                              @RequestParam(defaultValue = "0") int offset,
                              Model model) {

        LocalDate baseDate = LocalDate.now();
        LocalDate startDate = baseDate.plusDays((long) offset * days);
        LocalDate endDate = startDate.plusDays(days - 1);

        String formattedStartDate = startDate.format(DateTimeFormatter.ofPattern("dd/MM"));
        String formattedEndDate = endDate.format(DateTimeFormatter.ofPattern("dd/MM"));

        List<DayLuck> calendar = generateCalendar(days, startDate.getDayOfMonth(), startDate.getMonthValue());

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
            String dayOfWeek = getDayOfWeek(date);
            DayLuck dayLuck = new DayLuck(date.getDayOfMonth(), date.getMonthValue(), dayOfWeek);
            calendar.add(dayLuck);
        }
        return calendar;
    }

    private String getDayOfWeek(LocalDate date) {
        return switch (date.getDayOfWeek()) {
            case MONDAY -> "T2";
            case TUESDAY -> "T3";
            case WEDNESDAY -> "T4";
            case THURSDAY -> "T5";
            case FRIDAY -> "T6";
            case SATURDAY -> "T7";
            case SUNDAY -> "CN";
            default -> "";
        };
    }
}
