package com.dev.cinema;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.exceptions.EmailAlreadyRegisteredException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;

import java.time.LocalDateTime;
import java.util.List;


import org.apache.log4j.Logger;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Main.class);

        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        Movie movie = new Movie();
        movie.setTitle("Family instant");
        movie = movieService.add(movie);

        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(50);
        cinemaHall = cinemaHallService.add(cinemaHall);

        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        LocalDateTime showTime = LocalDateTime.of(2020,02,04,19,00);
        movieSession.setShowTime(showTime);
        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);
        movieSessionService.findAvailableSessions(1L,
                showTime.toLocalDate()).forEach(System.out::println);

        System.out.println(cinemaHall);

        AuthenticationService authenticationService =
                (AuthenticationService) injector.getInstance(AuthenticationService.class);
        User user = null;
        try {
            user = authenticationService.register("pavlo@yahoo.com", "1");
            System.out.println(user);
        } catch (EmailAlreadyRegisteredException e) {
            logger.error("Cannot add user to database");
        }
        try {
            User userRegistered = authenticationService.login("pavlo@yahoo.com", "1");
            System.out.println(userRegistered);
        } catch (AuthenticationException e) {
            logger.error("Wrong login or email");
        }
        ShoppingCartService shoppingCartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

        shoppingCartService.addSession(movieSession, user);
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        System.out.println(shoppingCart);

        OrderService orderService =
                (OrderService) injector.getInstance(OrderService.class);
        Order order =
                orderService.completeOrder(shoppingCart.getTickets(), user);
        List<Order> userOrders = orderService.getOrderHistory(user);
        System.out.print(userOrders);
    }
}
