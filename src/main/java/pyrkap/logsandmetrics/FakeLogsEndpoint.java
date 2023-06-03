package pyrkap.logsandmetrics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logs")
public class FakeLogsEndpoint {
    
    private Logger logger = LoggerFactory.getLogger(FakeLogsEndpoint.class);
    
    @GetMapping("/generate")
    @ResponseStatus(HttpStatus.OK)
    void generateLog() {}
}
