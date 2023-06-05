package pyrkap.logsandmetrics.domain.exceptions;

import java.util.UUID;

public class CyclistNotFoundException extends RuntimeException {
    public CyclistNotFoundException(UUID cyclistId) {
        super("Cyclist not found: id=" + cyclistId.toString());
    }
}
