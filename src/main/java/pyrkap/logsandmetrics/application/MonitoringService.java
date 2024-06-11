package pyrkap.logsandmetrics.application;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;
import pyrkap.logsandmetrics.infrastructure.externalservices.FakeExternalService;
import pyrkap.logsandmetrics.infrastructure.repository.FakeUserRepository;

@Service
public class MonitoringService {
    private final FakeExternalService fakeExternalService;
    private final MeterRegistry meterRegistry;

    public MonitoringService(
            FakeExternalService fakeExternalService,
            FakeUserRepository fakeUserRepository,
            MeterRegistry meterRegistry
    ) {
        this.fakeExternalService = fakeExternalService;
        this.meterRegistry = meterRegistry;
    }

    public void call() {
        fakeExternalService.call();
    }
}
