package com.dev.cinema.service;

import java.time.LocalDate;
import java.util.List;
import com.dev.cinema.model.Movie;

public interface MovieService {

    Movie add(Movie movie);

    List<Movie> getAll();

}
