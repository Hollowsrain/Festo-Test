package festo.test.donato.service;

import festo.test.donato.model.MovieModel;

import java.util.List;

public interface MovieService {

    /**
     * get list of movieModels
     * filters list
     * sorts list
     * @param years
     * @return sorted and filtered List of MovieModels
     */
    List<MovieModel> getMovies(String[] years);

    /**
     * gets a dummy list from dummy-list endpoint
     * @return List<MovieModel>
     */
    List<MovieModel> getDummyList();
}
