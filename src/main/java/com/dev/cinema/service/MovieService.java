package com.dev.cinema.service;

import com.dev.cinema.exceptions.DataProcessingException;
import java.util.List;
import com.dev.cinema.model.Movie;

public interface MovieService {

    Movie add(Movie movie);

    List<Movie> getAll() throws DataProcessingException;

}
