package com.dev.cinema.dao;

import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.CinemaHall;
import java.util.List;

public interface CinemHallDao {

    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll() throws DataProcessingException;

    CinemaHall getById(Long cinemaHallId);
}
