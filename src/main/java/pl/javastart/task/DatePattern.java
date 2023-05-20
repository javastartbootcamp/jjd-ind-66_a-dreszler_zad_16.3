package pl.javastart.task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

enum DatePattern {
    PATTERN1("yyyy-MM-dd"),
    PATTERN2("dd.MM.yyyy"),
    PATTERN3("t([+-]\\d+y)?([+-]\\d+M)?([+-]\\d+d)?([+-]\\d+h)?([+-]\\d+m)?([+-]\\d+s)?");

    final String pattern;

    DatePattern(String pattern) {
        this.pattern = pattern;
    }

    static String getPattern(String dateStr) {
        for (DatePattern patt : values()) {
            if (isValid(patt.pattern, dateStr)) {
                return patt.pattern;
            }
        }

        return null;
    }

    private static boolean isValid(String pattern, String dateStr) {
        if (dateStr.matches(pattern)) {
            return true;
        }

        try {
            DateFormat sdf = new SimpleDateFormat(pattern);
            sdf.setLenient(false);
            sdf.parse(dateStr);
        } catch (ParseException | IllegalArgumentException e) {
            return false;
        }

        return true;
    }
}
