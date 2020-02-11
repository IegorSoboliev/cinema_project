package com.dev.cinema.service.impl;

import com.dev.cinema.dao.CinemHallDao;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.service.CinemaHallService;

import java.util.List;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    @Inject
    private CinemHallDao cinemHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemHallDao.getAll();
    }
}
