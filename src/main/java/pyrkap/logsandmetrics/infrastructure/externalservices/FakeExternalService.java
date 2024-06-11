package pyrkap.logsandmetrics.infrastructure.externalservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FakeExternalService {

    private final Logger logger = LoggerFactory.getLogger(FakeExternalService.class);

    private final Random random = new Random();

    public ResponseEntity<Void> call() {
        sleep();
        return switch (random.nextInt(10)) {
            case 0, 1, 2, 3 -> callOk();
            case 4, 5, 6 -> callNotFound();
            case 7, 8 -> callInternalServerError();
            default -> callServiceUnavailable();
        };
    }

    private ResponseEntity<Void> callOk() {
        logger.info("External service called successfully");
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<Void> callNotFound() {
        logger.warn("External service called with error 404");
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<Void> callInternalServerError() {
        logger.error("External service called with error 500");
        return ResponseEntity.status(500).build();
    }

    private ResponseEntity<Void> callServiceUnavailable() {
        logger.error("External service called with error 503");
        return ResponseEntity.status(503).build();
    }

    private void sleep() {
        try {
            Thread.sleep(random.nextLong(550));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
