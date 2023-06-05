package pyrkap.logsandmetrics.domain.model;

import java.math.BigDecimal;
import java.time.Instant;

public record Route(
        Double kilometers,
        Instant startedAt,
        Instant finishedAt
) {
}
