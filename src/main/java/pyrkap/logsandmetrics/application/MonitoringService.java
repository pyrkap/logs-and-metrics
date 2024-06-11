package pyrkap.logsandmetrics.application;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import pyrkap.logsandmetrics.infrastructure.externalservices.FakeExternalService;
import pyrkap.logsandmetrics.infrastructure.repository.FakeUserRepository;

import java.time.Duration;

@Service
public class MonitoringService {
    private final FakeExternalService fakeExternalService;
    private final MeterRegistry meterRegistry;
    private final Timer timer;

    public MonitoringService(
            FakeExternalService fakeExternalService,
            FakeUserRepository fakeUserRepository,
            MeterRegistry meterRegistry
    ) {
        this.fakeExternalService = fakeExternalService;
        this.meterRegistry = meterRegistry;
        this.timer = Timer.builder("external_service_calls_time")
                .publishPercentiles(0.5, 0.75, 0.95, 0.98, 0.99)
                .serviceLevelObjectives(Duration.ofMillis(50), Duration.ofMillis(100), Duration.ofMillis(250), Duration.ofMillis(500))
                .register(meterRegistry);
        this.meterRegistry.gauge("users_online_count", fakeUserRepository, FakeUserRepository::getOnlineUsersCount);
        this.meterRegistry.gauge("users_online_average", fakeUserRepository, FakeUserRepository::getOnlineUsersAverageOverTime);
    }

    public void call() {;
        var response = timer.record(fakeExternalService::call);
        countCallsWithStatus(response.getStatusCode());
    }

    public void countCallsWithStatus(HttpStatusCode status) {
        meterRegistry.counter("external_service_calls_with_status", "status", Integer.toString(status.value())).increment();
    }
}
