package pyrkap.logsandmetrics.infrastructure.api.dtos;

import pyrkap.logsandmetrics.domain.model.Cyclist;

import java.util.List;
import java.util.stream.Collectors;

public class DetailedCyclistDto {
    String id;
    String name;
    Double kilometersDone;
    String timeOnBike;
    List<RouteDto> routes;

    private DetailedCyclistDto(String id, String name, Double kilometersDone, String timeOnBike, List<RouteDto> routes) {
        this.id = id;
        this.name = name;
        this.kilometersDone = kilometersDone;
        this.timeOnBike = timeOnBike;
        this.routes = routes;
    }
    
    public static DetailedCyclistDto of(Cyclist cyclist) {
        return new DetailedCyclistDto(
                cyclist.id().toString(),
                cyclist.name(),
                cyclist.summary().kilometers(), 
                cyclist.summary().timeOnBike().toString(),
                cyclist.routes().stream().map(RouteDto::of).collect(Collectors.toList())
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

    public List<RouteDto> getRoutes() {
        return routes;
    }
}
