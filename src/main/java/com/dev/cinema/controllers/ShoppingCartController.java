package com.dev.cinema.controllers;

import com.dev.cinema.dto.MovieSessionRequestDto;
import com.dev.cinema.dto.ShoppingCartResponseDto;
import com.dev.cinema.dto.TicketDto;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {
    private ShoppingCartService shoppingCartService;
    private MovieService movieService;
    private CinemaHallService cinemaHallService;
    private UserService userService;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  MovieService movieService, CinemaHallService cinemaHallService,
                                  UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
        this.userService = userService;
    }

    @PostMapping("/moviesession")
    public void addMovieSession(@RequestBody MovieSessionRequestDto movieSessionRequestDto,
                                @RequestParam("userId") Long userId) {
        MovieSession movieSession = new MovieSession();
        Movie movie = movieService.getById(movieSessionRequestDto.getMovieId());
        movieSession.setMovie(movie);
        CinemaHall cinemaHall =
                cinemaHallService.getById(movieSessionRequestDto.getCinemaHallId());
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setShowTime(LocalDateTime.parse(movieSessionRequestDto.getShowTime()));
        User user = userService.getById(userId);
        shoppingCartService.addSession(movieSession, user);
    }

    @GetMapping("/user-shoppingcart")
    public ShoppingCartResponseDto getByUserId(@RequestParam("userId") Long userId) {
        User user = userService.getById(userId);
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        List<TicketDto> ticketsDto = shoppingCart.getTickets()
                .stream()
                .map(this::transformToTicketsDto)
                .collect(Collectors.toList());
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();
        shoppingCartResponseDto.setUserId(userId);
        shoppingCartResponseDto.setTicketsDto(ticketsDto);
        return shoppingCartResponseDto;
    }

    private TicketDto transformToTicketsDto(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();
        MovieSession movieSession = ticket.getMovieSession();
        ticketDto.setMovieTitle(movieSession.getMovie().getTitle());
        ticketDto.setShowTime(movieSession.getShowTime().toString());
        ticketDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        return ticketDto;
    }
}
