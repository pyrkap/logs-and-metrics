package pyrkap.logsandmetrics.application;

import org.springframework.stereotype.Service;
import pyrkap.logsandmetrics.domain.exceptions.CyclistAlreadyExistsException;
import pyrkap.logsandmetrics.domain.exceptions.CyclistNotFoundException;
import pyrkap.logsandmetrics.domain.exceptions.InvalidIdException;
import pyrkap.logsandmetrics.domain.model.Cyclist;
import pyrkap.logsandmetrics.domain.model.Route;

import java.util.UUID;

@Service
public class CyclistFacade {

    private final CyclistRepository repository;

    public CyclistFacade(
            CyclistRepository cyclistRepository
    ) {
        this.repository = cyclistRepository;
    }

    public Cyclist getCyclist(String id) throws InvalidIdException, CyclistNotFoundException {
        UUID cyclistId = parseIdOrThrow(id);
        return repository.get(cyclistId);
    }

    public Cyclist addCyclist(String name) throws CyclistAlreadyExistsException {
        Cyclist cyclist = Cyclist.create(name);
        return repository.insert(cyclist);
    }

    public Cyclist addRoute(String cyclistId, Route route) throws InvalidIdException, CyclistNotFoundException {
        Cyclist c = getCyclist(cyclistId).update(route);
        return repository.update(c);
    }

    private UUID parseIdOrThrow(String id) throws InvalidIdException {
        try {
            return UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new InvalidIdException(id);
        }
    }

}
