package pyrkap.logsandmetrics.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pyrkap.logsandmetrics.api.dtos.MessageDto;

@RestController
@RequestMapping("/logs")
public class FakeLogsEndpoint {

    private final Logger logger = LoggerFactory.getLogger(FakeLogsEndpoint.class);

    @PostMapping("/info")
    @ResponseStatus(HttpStatus.OK)
    void info(@RequestBody MessageDto messageDto) {
        logger.info(messageDto.message());
    }

    @PostMapping("/warn")
    @ResponseStatus(HttpStatus.OK)
    void warn(@RequestBody MessageDto messageDto) {
        logger.warn(messageDto.message());
    }

    @PostMapping("/error")
    @ResponseStatus(HttpStatus.OK)
    void error(@RequestBody MessageDto messageDto) {
        logger.error(messageDto.message());
    }

    @PostMapping("/exception")
    @ResponseStatus(HttpStatus.OK)
    void fatal(@RequestBody MessageDto messageDto) {
        logger.error(messageDto.message(), fakeStackedExceptions());
    }

    private Throwable fakeStackedExceptions() {
        return new Exception(
                "Top level exception",
                new Exception(
                        "Mid level exception",
                        new Exception("Low level exception")
                )
        );
    }
}
