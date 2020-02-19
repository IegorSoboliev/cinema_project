package com.dev.cinema.controllers;

import com.dev.cinema.dto.CinemaHallDto;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.service.CinemaHallService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/cinemahalls")
public class CinemaHallController {
    private CinemaHallService cinemaHallService;

    public CinemaHallController(CinemaHallService cinemaHallService) {
        this.cinemaHallService = cinemaHallService;
    }

    @PostMapping("/add")
    public void addCinemaHall(CinemaHallDto cinemaHallDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(cinemaHallDto.getCapacity());
        cinemaHall.setDescription(cinemaHallDto.getDescription());
        cinemaHallService.add(cinemaHall);
    }

    @GetMapping("/all")
    public List<CinemaHallDto> getAllCinemaHallsDto() {
        return cinemaHallService.getAll()
                .stream()
                .map(this::transformToDto)
                .collect(Collectors.toList());
    }

    private CinemaHallDto transformToDto(CinemaHall cinemaHall) {
        CinemaHallDto cinemaHallDto = new CinemaHallDto();
        cinemaHallDto.setCapacity(cinemaHall.getCapacity());
        cinemaHallDto.setDescription(cinemaHall.getDescription());
        return cinemaHallDto;
    }
}
