package pyrkap.logsandmetrics.infrastructure.api.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import pyrkap.logsandmetrics.domain.model.Route;

import java.time.Instant;

public class RouteDto {
    Double kilometers;
    Long startedAt;
    Long finishedAt;

    @JsonCreator
    public RouteDto(Double kilometers, Long startedAt, Long finishedAt) {
        this.kilometers = kilometers;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
    }

    public Route toRoute() {
        return new Route(kilometers, Instant.ofEpochMilli(startedAt), Instant.ofEpochMilli(finishedAt));
    }

    public static RouteDto of(Route route) {
        return new RouteDto(
                route.kilometers(),
                route.startedAt().toEpochMilli(),
                route.finishedAt().toEpochMilli()
        );
    }

    public Double getKilometers() {
        return kilometers;
    }

    public Long getStartedAt() {
        return startedAt;
    }

    public Long getFinishedAt() {
        return finishedAt;
    }
}
