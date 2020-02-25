package com.dev.cinema.controllers;

import com.dev.cinema.dao.RoleDao;
import com.dev.cinema.dao.UserDao;
import com.dev.cinema.exceptions.EmailAlreadyRegisteredException;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.Role;
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

import javax.annotation.PostConstruct;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInjectionController {
    private UserService userService;
    private UserDao userDao;
    private AuthenticationService authenticationService;
    private MovieService movieService;
    private CinemaHallService cinemaHallService;
    private MovieSessionService movieSessionService;
    private ShoppingCartService shoppingCartService;
    private OrderService orderService;
    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;

    public DataInjectionController(UserService userService, UserDao userDao, AuthenticationService
            authenticationService, MovieService movieService, CinemaHallService cinemaHallService,
                                   MovieSessionService movieSessionService, ShoppingCartService
                                           shoppingCartService, OrderService orderService, RoleDao
                                           roleDao, PasswordEncoder passwordEncoder) {

        this.userService = userService;
        this.userDao = userDao;
        this.authenticationService = authenticationService;
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
        this.movieSessionService = movieSessionService;
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public String addData() throws EmailAlreadyRegisteredException {
        Role adminRole = new Role();
        adminRole.setRoleName("ADMIN");
        roleDao.add(adminRole);
        Role userRole = new Role();
        userRole.setRoleName("USER");
        roleDao.add(userRole);

        User admin = new User();
        admin.setEmail("sofia@yahoo.com");
        admin.setPassword(passwordEncoder.encode("1"));
        admin.addRole(roleDao.getByRoleName("USER"));
        userDao.add(admin);
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
        return orderService.getOrdersHistory(user).toString();
    }
}
