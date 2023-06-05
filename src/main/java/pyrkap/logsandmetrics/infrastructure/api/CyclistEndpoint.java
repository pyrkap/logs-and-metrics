package pyrkap.logsandmetrics.infrastructure.api;

import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pyrkap.logsandmetrics.application.CyclistFacade;
import pyrkap.logsandmetrics.infrastructure.api.dtos.DetailedCyclistDto;
import pyrkap.logsandmetrics.infrastructure.api.dtos.NewCyclistDto;
import pyrkap.logsandmetrics.infrastructure.api.dtos.RouteDto;
import pyrkap.logsandmetrics.infrastructure.api.dtos.SimplifiedCyclistDto;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/cyclist")
public class CyclistEndpoint {

    private final CyclistFacade cyclistFacade;

    public CyclistEndpoint(CyclistFacade cyclistFacade) {
        this.cyclistFacade = cyclistFacade;
    }

    @PutMapping(value = "/new", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    @Timed("cyclists.create")
    public SimplifiedCyclistDto createCyclist(@RequestBody NewCyclistDto newCyclistDto) {
        return SimplifiedCyclistDto.of(cyclistFacade.addCyclist(newCyclistDto.name()));
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    @Timed("cyclists.get")
    public SimplifiedCyclistDto getCyclist(@PathVariable("id") String id) {
        return SimplifiedCyclistDto.of(cyclistFacade.getCyclist(id));
    }

    @GetMapping(value = "/{id}/details", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    @Timed("cyclists.details")
    public DetailedCyclistDto getDetailedCyclist(@PathVariable("id") String id) {
        return DetailedCyclistDto.of(cyclistFacade.getCyclist(id));
    }

    @PostMapping(value = "{id}/addRoute", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    @Timed("cyclists.update")
    public DetailedCyclistDto addRoute(@PathVariable("id") String id, @RequestBody RouteDto routeDto) {
        return DetailedCyclistDto.of(cyclistFacade.addRoute(id, routeDto.toRoute()));
    }

}
