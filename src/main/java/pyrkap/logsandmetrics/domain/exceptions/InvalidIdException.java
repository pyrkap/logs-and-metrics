package pyrkap.logsandmetrics.domain.exceptions;

public class InvalidIdException extends RuntimeException {
    public InvalidIdException(String invalidId) {
        super("Invalid cyclist id=" + invalidId);
    }
}
