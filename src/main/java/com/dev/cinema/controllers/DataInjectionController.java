package com.dev.cinema.controllers;

import com.dev.cinema.exceptions.EmailAlreadyRegisteredException;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/injection")
public class DataInjectionController {
    private UserService userService;
    private AuthenticationService authenticationService;
    private MovieService movieService;
    private CinemaHallService cinemaHallService;
    private MovieSessionService movieSessionService;
    private ShoppingCartService shoppingCartService;
    private OrderService orderService;

    public DataInjectionController(UserService userService, AuthenticationService
            authenticationService, MovieService movieService, CinemaHallService cinemaHallService,
                                   MovieSessionService movieSessionService, ShoppingCartService
                                           shoppingCartService, OrderService orderService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
        this.movieSessionService = movieSessionService;
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
    }

    @GetMapping("/data")
    public String addData() throws EmailAlreadyRegisteredException {
        authenticationService.register("pavlo@yahoo.com", "1");
        Movie movie = new Movie();
        movie.setTitle("True family");
        movie.setDescription("Nice melodrama");
        movieService.add(movie);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setDescription("Red");
        cinemaHallService.add(cinemaHall);
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(cinemaHall);
        LocalDateTime showTime = LocalDateTime.now().plusHours(2L);
        movieSession.setShowTime(showTime);
        movieSessionService.add(movieSession);
        User user = userService.getByEmail("pavlo@yahoo.com");
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        shoppingCartService.addSession(movieSession, user);
        orderService.completeOrder(user);
        return orderService.getOrdersHistory(user.getId()).toString();
    }
}
