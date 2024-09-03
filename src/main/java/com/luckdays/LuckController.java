package com.luckdays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LuckController {
    @Value("classpath:tooltip.txt")
    private org.springframework.core.io.Resource tooltipFile;

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/calendar";
    }

    @GetMapping("/calendar")
    public String getCalendar(@RequestParam(defaultValue = "7") int days,
                              @RequestParam(defaultValue = "0") int offset,
                              Model model) {

        LocalDate baseDate = LocalDate.now();
        LocalDate startDate = baseDate.plusDays((long) offset * days);
        LocalDate endDate = startDate.plusDays(days - 1);

        String formattedStartDate = startDate.format(DateTimeFormatter.ofPattern("dd/MM"));
        String formattedEndDate = endDate.format(DateTimeFormatter.ofPattern("dd/MM"));

        List<DayLuck> calendar = generateCalendar(days, startDate.getDayOfMonth(), startDate.getMonthValue(), startDate.getYear());
        List<String> lunarDates = new ArrayList<>();

        for (DayLuck dayLuck : calendar) {
            int[] lunarDate = LunarUtils.convertSolar2Lunar(dayLuck.getDay(), dayLuck.getMonth(), dayLuck.getYear(), 7.0);
            String lunarDateString = lunarDate[0] + "/" + lunarDate[1];
            lunarDates.add(lunarDateString);

            // Cập nhật lunarDay và lunarMonth trong DayLuck
            dayLuck.setLunarDay(lunarDate[0]);
            dayLuck.setLunarMonth(lunarDate[1]);
        }

        String tooltipText = "";
        try {
            tooltipText = new String(Files.readAllBytes(Paths.get(tooltipFile.getURI())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addAttribute("tooltipText", tooltipText);
        model.addAttribute("calendar", calendar);
        model.addAttribute("startDate", formattedStartDate);
        model.addAttribute("endDate", formattedEndDate);
        model.addAttribute("offset", offset);
        model.addAttribute("lunarDates", lunarDates);

        return "calendar";
    }

    private List<DayLuck> generateCalendar(int days, int startDay, int month, int year) {
        List<DayLuck> calendar = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            LocalDate date = LocalDate.of(year, month, startDay).plusDays(i);
            String dayOfWeek = getDayOfWeek(date);
            int[] lunarDate = LunarUtils.convertSolar2Lunar(date.getDayOfMonth(), date.getMonthValue(), date.getYear(), 7.0);
            DayLuck dayLuck = new DayLuck(date.getDayOfMonth(), date.getMonthValue(), date.getYear(), lunarDate[0], lunarDate[1], dayOfWeek);
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
