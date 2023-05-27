package pyrkap.logsandmetrics;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FakeLoggingService {
    
    Logger logger = LoggerFactory.getLogger(FakeLoggingService.class);
    
    @PostConstruct
    void log() {
        logger.info("Test message 1");
        logger.info("Test message 2");
        logger.info("Test message 3");
        logger.info("Test message 4");
        logger.info("Test message 5");
        logger.info("Test message 6");
        logger.info("Test message 7");
        logger.info("Test message 8");
        logger.info("Test message 9");
        logger.info("Test message 10");
        logger.warn("WARN message");
        logger.error("ERROR message");
    }
}

