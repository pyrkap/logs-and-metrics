package pyrkap.logsandmetrics.domain.model;

import java.time.Duration;

public record Summary(
        Double kilometers,
        Duration timeOnBike
) {
    public static Summary emptySummary() {
        return new Summary(0d, Duration.ZERO);
    }

    public Summary addRoute(Route route) {
        return new Summary(
                this.kilometers + route.kilometers(),
                this.timeOnBike.plus(Duration.between(route.startedAt(), route.finishedAt()))
        );
    }
}
