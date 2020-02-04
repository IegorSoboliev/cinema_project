package com.dev.cinema.service.impl;

import java.util.List;

import com.dev.cinema.dao.MovieDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.Movie;
import com.dev.cinema.service.MovieService;
import org.apache.log4j.Logger;

@Service
public class MovieServiceImpl implements MovieService {
    @Inject
    private static MovieDao movieDao;
    private static final Logger LOGGER = Logger.getLogger(MovieServiceImpl.class);


    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    public List<Movie> getAll() {
        try {
            return movieDao.getAll();
        } catch (DataProcessingException e) {
            LOGGER.error("Cannot show all movies from database", e);
            throw new RuntimeException();
        }
    }
}
