package com.luckdays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LuckController {

    private final ResourceLoader resourceLoader;

    public LuckController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("activePage","about");
        return "about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("activePage","contact");
        return "contact";
    }

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/calendar";
    }

    @GetMapping("/calendar")
    public String getCalendar(@RequestParam(defaultValue = "7") int days,
                              @RequestParam(defaultValue = "0") int offset,
                              Model model) {

        LocalDate startDate = calculateStartDate(days, offset);
        LocalDate endDate = startDate.plusDays(days - 1);

        List<DayLuck> calendar = generateCalendar(days, startDate);
        List<String> lunarDates = updateLunarDates(calendar);

        String helpInfo = loadTxtFile();

        model.addAttribute("helpInfo", helpInfo);
        model.addAttribute("calendar", calendar);
        model.addAttribute("startDate", formatDate(startDate));
        model.addAttribute("endDate", formatDate(endDate));
        model.addAttribute("days", days);
        model.addAttribute("offset", offset);
        model.addAttribute("lunarDates", lunarDates);
        model.addAttribute("customSequence", DayLuck.getCustomSequence());

        return "calendar";
    }

    private LocalDate calculateStartDate(int days, int offset) {
        LocalDate baseDate = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        return baseDate.plusDays((long) offset * days);
    }

    private List<DayLuck> generateCalendar(int days, LocalDate startDate) {
        List<DayLuck> calendar = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            LocalDate date = startDate.plusDays(i);
            String dayOfWeek = getDayOfWeek(date);
            int[] lunarDate = LunarUtils.convertSolar2Lunar(date.getDayOfMonth(), date.getMonthValue(), date.getYear(), 7.0); // int[day, month, year, leap]
            DayLuck dayLuck = new DayLuck(date.getDayOfMonth(), date.getMonthValue(), date.getYear(), lunarDate[0], lunarDate[1], dayOfWeek);
            calendar.add(dayLuck);
        }
        return calendar;
    }

    private List<String> updateLunarDates(List<DayLuck> calendar) {
        List<String> lunarDates = new ArrayList<>();
        for (DayLuck dayLuck : calendar) {
            int[] lunarDate = LunarUtils.convertSolar2Lunar(dayLuck.getDay(), dayLuck.getMonth(), dayLuck.getYear(), 7.0);
            String lunarDateString = lunarDate[0] + "/" + lunarDate[1] + "/" + lunarDate[2];
            lunarDates.add(lunarDateString);
            dayLuck.setLunarDay(lunarDate[0]);
            dayLuck.setLunarMonth(lunarDate[1]);
        }
        return lunarDates;
    }

    private String loadTxtFile() {
        try {
            Resource resource = resourceLoader.getResource("classpath:helpInfo.txt");
            InputStream inputStream = resource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
            return "failed to load txt file";
        }
    }

    private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd/MM"));
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
        };
    }
}