package pyrkap.logsandmetrics.application;

import pyrkap.logsandmetrics.domain.exceptions.CyclistAlreadyExistsException;
import pyrkap.logsandmetrics.domain.exceptions.CyclistNotFoundException;
import pyrkap.logsandmetrics.domain.model.Cyclist;

import java.util.UUID;

public interface CyclistRepository {
    Cyclist insert(Cyclist cyclist) throws CyclistAlreadyExistsException;
    Cyclist update(Cyclist cyclist) throws CyclistNotFoundException;
    Cyclist get(UUID cyclistId) throws CyclistNotFoundException;
}
