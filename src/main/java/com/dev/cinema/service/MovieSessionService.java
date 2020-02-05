package com.dev.cinema.service;

import java.time.LocalDate;
import java.util.List;

import com.dev.cinema.model.MovieSession;

public interface MovieSessionService {

    MovieSession add (MovieSession movieSession);

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate showTime);
}
