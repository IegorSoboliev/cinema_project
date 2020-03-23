package com.dev.cinema.service.impl;

import com.dev.cinema.dao.CinemHallDao;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.service.CinemaHallService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    private CinemHallDao cinemHallDao;

    CinemaHallServiceImpl(CinemHallDao cinemHallDao) {
        this.cinemHallDao = cinemHallDao;
    }

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemHallDao.getAll();
    }

    @Override
    public CinemaHall getById(Long cinemaHallId) {
        return cinemHallDao.getById(cinemaHallId);
    }
}
