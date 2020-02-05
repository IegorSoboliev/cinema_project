package com.dev.cinema.dao;

import java.time.LocalDate;
import java.util.List;

import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.MovieSession;

public interface MovieSessionDao {

    MovieSession add (MovieSession movieSession);

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate showTime) throws DataProcessingException;
}
