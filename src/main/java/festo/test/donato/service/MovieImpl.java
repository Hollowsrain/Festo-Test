package festo.test.donato.service;

import festo.test.donato.model.MovieModel;
import festo.test.donato.utils.MovieUtils;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieImpl implements MovieService {

    private final RestTemplate restTemplate;

    private final String API_KEY = "dc785020";

    private final String DUMMY_URL = "http://localhost:8080/movies/dummy-list";

    public MovieImpl(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }


    @Override
    public List<MovieModel> getMovies(String[] years) {
        List<MovieModel> movies = new ArrayList<>();
        for (String year : years) {
            movies.addAll(getMovieListByYear(year));
        }

        movies = MovieUtils.filterByImdbRating(movies);
        MovieUtils.sortByNameAndRating(movies);
        return movies;
    }
    @Override
    public List<MovieModel> getDummyList() {
        List<MovieModel> movies = new ArrayList<>();
        movies.add(MovieUtils.getMovieByImdbId("tt3896198", restTemplate));
        movies.add(MovieUtils.getMovieByImdbId("tt0848228", restTemplate));
        return movies;
    }

    /**
     * @param year should be used, but since api doesn't return list by year it's unused
     *             uses restTemplate to get json list
     *             maps json list to List<MovieModel>
     *             return list of movieModels
     */
    private List<MovieModel> getMovieListByYear(String year) {
        // this would be used if api returned movies list by year
        String url = "http://www.omdbapi.com/?apikey=" + API_KEY + "&y=" + year;

        ResponseEntity<List<MovieModel>> responseEntity =
                restTemplate.exchange(
                        DUMMY_URL,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        }
                );
        return responseEntity.getBody();
    }
}
