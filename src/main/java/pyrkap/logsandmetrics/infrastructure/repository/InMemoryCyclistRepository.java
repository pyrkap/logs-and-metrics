package pyrkap.logsandmetrics.infrastructure.repository;

import io.micrometer.core.annotation.Timed;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import pyrkap.logsandmetrics.application.CyclistRepository;
import pyrkap.logsandmetrics.domain.exceptions.CyclistAlreadyExistsException;
import pyrkap.logsandmetrics.domain.exceptions.CyclistNotFoundException;
import pyrkap.logsandmetrics.domain.model.Cyclist;
import pyrkap.logsandmetrics.domain.model.Summary;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Repository
public class InMemoryCyclistRepository implements CyclistRepository {

    private final Logger logger = LoggerFactory.getLogger(InMemoryCyclistRepository.class);

    private final Map<UUID, Cyclist> map = new HashMap<>();
    private final Random random = new Random();

    @Override
    @Timed(value = "cyclist.repository", extraTags = {"method", "insert"})
    public Cyclist insert(Cyclist cyclist) {
        sleepRandom();
        if (cyclistExistsByName(cyclist.name()))
            throw new CyclistAlreadyExistsException(cyclist.name());
        logger.info("Adding new cyclist: id={}, name={}", cyclist.id(), cyclist.name());
        return save(cyclist);
    }

    @Override
    @Timed(value = "cyclist.repository", extraTags = {"method", "update"})
    public Cyclist update(Cyclist cyclist) {
        sleepRandom();
        if (!cyclistExistsById(cyclist.id()))
            throw new CyclistNotFoundException(cyclist.id());
        logger.info("Updating cyclist: id={}, name={}", cyclist.id(), cyclist.name());
        return save(cyclist);
    }

    @Override
    @Timed(value = "cyclist.repository", extraTags = {"method", "get"})
    public Cyclist get(UUID cyclistId) {
        sleepRandom();
        if (!cyclistExistsById(cyclistId))
            throw new CyclistNotFoundException(cyclistId);
        else return map.get(cyclistId);
    }

    private Cyclist save(Cyclist cyclist) {
        map.put(cyclist.id(), cyclist);
        return cyclist;
    }

    private Boolean cyclistExistsById(UUID cyclistId) {
        return map.containsKey(cyclistId);
    }

    private Boolean cyclistExistsByName(String name) {
        return map.values().stream().anyMatch(savedCyclist -> savedCyclist.name().equals(name));
    }

    private void sleepRandom() {
        try {
            Thread.sleep(random.nextLong(300));
        } catch (InterruptedException e) {
        }
    }

    @PostConstruct
    private void createTestCyclists() {
        Map<UUID, String> testCyclists = new HashMap<>();
        testCyclists.put(UUID.fromString("aa8a76b4-9d22-403e-98f5-7145232b025d"), "cyclist_1");
        testCyclists.put(UUID.fromString("47bcad33-34ab-4498-8700-de09196de5c1"), "cyclist_2");
        testCyclists.put(UUID.fromString("b4bfbcb7-db3a-4083-9f01-de5942613242"), "cyclist_3");
        testCyclists.put(UUID.fromString("63a813da-1a03-4ce2-be90-f4a02ce773b0"), "cyclist_4");
        testCyclists.put(UUID.fromString("8e0ac7cb-651a-4080-abc9-1d3ec8c233d9"), "cyclist_5");
        testCyclists.put(UUID.fromString("8409a835-0e3f-41e5-8928-ae22eab5bc48"), "cyclist_6");
        testCyclists.put(UUID.fromString("0b4d4e8f-2bcc-4391-a944-43e2039cef4a"), "cyclist_7");
        testCyclists.put(UUID.fromString("88a2c6a7-8353-4570-b72e-a8c218720d89"), "cyclist_8");
        testCyclists.put(UUID.fromString("492c82c9-df61-4ad3-80aa-e3fb6c0d00d2"), "cyclist_9");
        testCyclists.put(UUID.fromString("91fc7520-fdb6-460a-beec-a43463cc8bb1"), "cyclist_10");
        testCyclists.forEach((cyclistId, cyclistName) -> insert(new Cyclist(
                cyclistId, cyclistName, Collections.emptyList(), Summary.emptySummary()
        )));
    }
}
