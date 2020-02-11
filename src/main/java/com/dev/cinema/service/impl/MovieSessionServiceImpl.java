package com.dev.cinema.service.impl;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.MovieSessionService;

import java.time.LocalDate;
import java.util.List;

public class MovieSessionServiceImpl implements MovieSessionService {
    @Inject
    private MovieSessionDao movieSessionDao;

    @Override
    public MovieSession add(MovieSession movieSession) {
        return movieSessionDao.add(movieSession);
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate showTime) {
        return movieSessionDao.findAvailableSessions(movieId, showTime);
    }
}
