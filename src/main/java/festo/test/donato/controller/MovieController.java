package festo.test.donato.controller;

import festo.test.donato.model.MovieModel;
import festo.test.donato.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping(produces = "application/json")
    public List<MovieModel> getMovies(@RequestParam String[] years) {
        return movieService.getMovies(years);
    }

    @GetMapping(value = "/dummy-list", produces = "application/json")
    public List<MovieModel> getDummyList() {
        return movieService.getDummyList();
    }
}
