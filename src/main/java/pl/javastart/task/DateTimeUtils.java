package pl.javastart.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class DateTimeUtils {
    private static final DateTimeFormatter OUTPUT_DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    static ZonedDateTime parseDateTime(String[] splitDateTime, String pattern) {
        if (pattern.equals(DatePattern.PATTERN3.pattern)) {
            return parseShortDateTime(splitDateTime);
        }
        return parseStandardDateTime(splitDateTime, pattern);
    }

    private static ZonedDateTime parseShortDateTime(String[] splitDateTime) {
        Matcher matcher = Pattern.compile(DatePattern.PATTERN3.pattern).matcher(splitDateTime[0]);
        matcher.matches();
        ZonedDateTime dateTime = ZonedDateTime.now();
        for (TimeUnit timeUnit : TimeUnit.values()) {
            String timeUnitStr = matcher.group(timeUnit.getValue());
            if (timeUnitStr != null) {
                int deltaTime = parseTimeUnit(timeUnitStr);
                dateTime = getModifiedDateTime(timeUnit, deltaTime, dateTime);
            }
        }
        return dateTime;
    }

    private static ZonedDateTime getModifiedDateTime(TimeUnit timeUnit, int deltaTime, ZonedDateTime dateTime) {
        return switch (timeUnit) {
            case YEAR -> dateTime.plusYears(deltaTime);
            case MONTH -> dateTime.plusMonths(deltaTime);
            case DAY -> dateTime.plusDays(deltaTime);
            case HOUR -> dateTime.plusHours(deltaTime);
            case MINUTE -> dateTime.plusMinutes(deltaTime);
            case SECOND -> dateTime.plusSeconds(deltaTime);
        };
    }

    private static int parseTimeUnit(String timeUnit) {
        return Integer.parseInt(timeUnit.replaceAll("[^([+-]\\d+)]", ""));
    }

    private static ZonedDateTime parseStandardDateTime(String[] splitDateTime, String pattern) {
        DateTimeFormatter datePattern = DateTimeFormatter.ofPattern(pattern);
        LocalDate date = LocalDate.parse(splitDateTime[0], datePattern);
        LocalTime time;
        if (splitDateTime.length == 2) {
            time = LocalTime.parse(splitDateTime[1]);
        } else {
            time = LocalTime.of(0, 0);
        }

        return ZonedDateTime.of(date, time, ZoneId.systemDefault());
    }

    static String formatDateTime(ZonedDateTime dateTime) {
        return dateTime.format(OUTPUT_DTF);
    }

    static Map<String, ZonedDateTime> convertDateTime(ZonedDateTime dateTime) {
        Map<String, ZonedDateTime> convertedDateTimes = new LinkedHashMap<>();
        for (TimeZone zone : TimeZone.values()) {
            convertedDateTimes.put(zone.getPlName(), dateTime.withZoneSameInstant(ZoneId.of(zone.getZoneId())));
        }

        return convertedDateTimes;
    }
}
