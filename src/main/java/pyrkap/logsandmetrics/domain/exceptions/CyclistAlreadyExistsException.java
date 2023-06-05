package pyrkap.logsandmetrics.domain.exceptions;

public class CyclistAlreadyExistsException extends RuntimeException {
    public CyclistAlreadyExistsException(String name) {
        super("Cyclist already exists: name=" + name);
    }
}
