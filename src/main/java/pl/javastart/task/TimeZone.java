package pl.javastart.task;

enum TimeZone {
    UTC("UTC", "UTC"),
    LONDON("Londyn", "Europe/London"),
    LOS_ANGELES("Los Angeles", "America/Los_Angeles"),
    SYDNEY("Sydney", "Australia/Sydney");

    private final String plName;
    private final String zoneId;

    TimeZone(String plName, String zoneId) {
        this.plName = plName;
        this.zoneId = zoneId;
    }

    String getZoneId() {
        return zoneId;
    }

    String getPlName() {
        return plName;
    }
}
