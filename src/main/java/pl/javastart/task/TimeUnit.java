package pl.javastart.task;

enum TimeUnit {
    YEAR(1),
    MONTH(2),
    DAY(3),
    HOUR(4),
    MINUTE(5),
    SECOND(6);

    private final int value;

    TimeUnit(int value) {
        this.value = value;
    }

    int getValue() {
        return value;
    }
}
