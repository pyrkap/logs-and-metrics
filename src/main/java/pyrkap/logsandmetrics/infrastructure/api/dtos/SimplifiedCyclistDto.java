package pyrkap.logsandmetrics.infrastructure.api.dtos;

import pyrkap.logsandmetrics.domain.model.Cyclist;

public class SimplifiedCyclistDto {
    String id;
    String name;
    Double kilometersDone;
    String timeOnBike;

    private SimplifiedCyclistDto(String id, String name, Double kilometersDone, String timeOnBike) {
        this.id = id;
        this.name = name;
        this.kilometersDone = kilometersDone;
        this.timeOnBike = timeOnBike;
    }
    
    public static SimplifiedCyclistDto of(Cyclist cyclist) {
        return new SimplifiedCyclistDto(
                cyclist.id().toString(),
                cyclist.name(),
                cyclist.summary().kilometers(),
                cyclist.summary().timeOnBike().toString()
        );
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getKilometersDone() {
        return kilometersDone;
    }

    public String getTimeOnBike() {
        return timeOnBike;
    }
}
