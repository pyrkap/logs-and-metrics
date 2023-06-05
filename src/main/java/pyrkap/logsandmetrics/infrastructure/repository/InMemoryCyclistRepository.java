package pyrkap.logsandmetrics.infrastructure.repository;

import org.springframework.stereotype.Repository;
import pyrkap.logsandmetrics.application.CyclistRepository;
import pyrkap.logsandmetrics.domain.exceptions.CyclistAlreadyExistsException;
import pyrkap.logsandmetrics.domain.exceptions.CyclistNotFoundException;
import pyrkap.logsandmetrics.domain.model.Cyclist;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Repository
public class InMemoryCyclistRepository implements CyclistRepository {

    private Map<UUID, Cyclist> map = new HashMap<>();
    private Random random = new Random();

    @Override
    public Cyclist insert(Cyclist cyclist) {
        sleepRandom();
        if (cyclistExistsByName(cyclist.name()))
            throw new CyclistAlreadyExistsException(cyclist.name());
        return save(cyclist);
    }

    @Override
    public Cyclist update(Cyclist cyclist) {
        sleepRandom();
        if (!cyclistExistsById(cyclist.id()))
            throw new CyclistNotFoundException(cyclist.id());
        return save(cyclist);
    }

    @Override
    public Cyclist get(UUID cyclistId) {
        sleepRandom();
        if(!cyclistExistsById(cyclistId))
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
}
