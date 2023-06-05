package pyrkap.logsandmetrics.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public record Cyclist(
        UUID id,
        String name,
        List<Route> routes,
        Summary summary
) {
    public static Cyclist create(String name) {
        return new Cyclist(
                UUID.randomUUID(),
                name,
                Collections.emptyList(),
                Summary.emptySummary()
        );
    }
    
    public Cyclist update(Route route) {
        return new Cyclist(
                this.id,
                this.name,
                updateRoutes(this.routes, route),
                summary.addRoute(route)
        );
    }

    private static List<Route> updateRoutes(List<Route> currentRoutes, Route newRoute) {
        List<Route> routes = new ArrayList<Route>(currentRoutes);
        routes.add(newRoute);
        return Collections.unmodifiableList(routes);
    }
}
