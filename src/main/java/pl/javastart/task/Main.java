package pl.javastart.task;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run(new Scanner(System.in));
    }

    public void run(Scanner scanner) {
        System.out.println("Podaj czas:");
        String timeStr = scanner.nextLine();
        String[] splitDateTime = timeStr.split(" ");
        String pattern = null;
        if (splitDateTime.length != 0 && splitDateTime.length <= 2) {
            pattern = DatePattern.getPattern(splitDateTime[0]);
        }

        if (pattern != null) {
            ZonedDateTime localDateTime = DateTimeUtils.parseDateTime(splitDateTime, pattern);
            Map<String, ZonedDateTime> convertedDateTimes = DateTimeUtils.convertDateTime(localDateTime);
            System.out.println("Czas lokalny: " + DateTimeUtils.formatDateTime(localDateTime));
            printConvertedDateTimes(convertedDateTimes);
        } else {
            System.out.println("Nieprawidłowy format daty i godziny. Prawidłowe formaty:\nyyyy-MM-dd HH:mm:ss\n" +
                    "yyyy-MM-dd\ndd.MM.yyyy HH:mm:ss\nt+/-y+/-M+/-d+/-h+/-m+/-s");
        }
    }

    private void printConvertedDateTimes(Map<String, ZonedDateTime> convertedDateTimes) {
        for (String zoneName : convertedDateTimes.keySet()) {
            System.out.println(zoneName + ": " + DateTimeUtils.formatDateTime(convertedDateTimes.get(zoneName)));
        }
    }
}