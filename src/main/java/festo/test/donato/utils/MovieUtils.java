package festo.test.donato.utils;

import festo.test.donato.model.MovieModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MovieUtils {

    /**
     * gets json response of movie, maps to MovieModel object
     *
     * @param id
     * @param restTemplate
     * @return
     */
    public static MovieModel getMovieByImdbId(String id, RestTemplate restTemplate) {
        ResponseEntity<MovieModel> responseEntity = restTemplate.getForEntity("http://www.omdbapi.com/?i=" + id + "&apikey=dc785020", MovieModel.class);
        return responseEntity.getBody();
    }

    /**
     * gets average rating from list of movies
     *
     * @param movies
     * @return average rating of type BigDecimal
     */
    public static BigDecimal getMoviesAverageRating(List<MovieModel> movies) {
        Function<MovieModel, BigDecimal> mapper = MovieModel::getImdbRating;
        BigDecimal ratingSum = movies.stream()
                .map(mapper)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return ratingSum.divide(BigDecimal.valueOf(movies.size()));
    }

    public static List<MovieModel> filterByImdbRating(List<MovieModel> movies) {
        BigDecimal average = getMoviesAverageRating(movies);
        return movies.stream()
                .filter(movie -> movie.getImdbRating().compareTo(average) > 0)
                .collect(Collectors.toList());
    }

    /**
     * @param movies given to sort by title and imdbRating
     */
    public static void sortByNameAndRating(List<MovieModel> movies) {
        Comparator<MovieModel> comparator = Comparator.comparing(MovieModel::getTitle);
        comparator.thenComparing(MovieModel::getImdbRating);
        movies.sort(comparator);
    }
}
