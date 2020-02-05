package com.dev.cinema.dao;

import java.util.List;

import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.CinemaHall;

public interface CinemHallDao {

    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll() throws DataProcessingException;
}
