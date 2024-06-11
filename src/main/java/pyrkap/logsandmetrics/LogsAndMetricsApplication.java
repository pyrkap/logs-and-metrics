package pyrkap.logsandmetrics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pyrkap.logsandmetrics.application.MonitoringService;

@SpringBootApplication
public class LogsAndMetricsApplication {

    public LogsAndMetricsApplication(MonitoringService monitoringService) {
        new Thread(() -> {
            while (true) {
                monitoringService.call();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        SpringApplication.run(LogsAndMetricsApplication.class, args);
    }

}
