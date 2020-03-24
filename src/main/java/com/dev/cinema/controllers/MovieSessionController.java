package com.dev.cinema.controllers;

import com.dev.cinema.dto.MovieSessionRequestDto;
import com.dev.cinema.dto.MovieSessionResponseDto;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/moviesessions")
public class MovieSessionController {
    private MovieSessionService movieSessionService;
    private MovieService movieService;
    private CinemaHallService cinemaHallService;

    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieService movieService, CinemaHallService cinemaHallService) {
        this.movieSessionService = movieSessionService;
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    @PostMapping
    public void addMovieSession(@RequestBody @Valid MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = new MovieSession();
        Movie movie = movieService.getById(movieSessionRequestDto.getMovieId());
        movieSession.setMovie(movie);
        CinemaHall cinemaHall =
                cinemaHallService.getById(movieSessionRequestDto.getCinemaHallId());
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setShowTime(LocalDateTime.parse(movieSessionRequestDto.getShowTime()));
        movieSessionService.add(movieSession);
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getMovieSessions(@RequestParam("movieId") Long movieId,
                                                      @RequestParam("date") LocalDate date) {
        return movieSessionService.findAvailableSessions(movieId, date)
               .stream()
               .map(this::transformToDto)
               .collect(Collectors.toList());
    }

    private MovieSessionResponseDto transformToDto(MovieSession movieSession) {
        MovieSessionResponseDto movieSessionResponseDto = new MovieSessionResponseDto();
        movieSessionResponseDto.setMovieTitle(movieSession.getMovie().getTitle());
        movieSessionResponseDto.setShowTime(movieSession.getShowTime().toString());
        movieSessionResponseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        return movieSessionResponseDto;
    }
}
