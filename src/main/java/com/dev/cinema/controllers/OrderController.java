package com.dev.cinema.controllers;

import com.dev.cinema.dto.OrderDto;
import com.dev.cinema.dto.TicketDto;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;
    private UserService userService;

    public OrderController(OrderService orderService,
                           UserService userService,
                           ShoppingCartService shoppingCartService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping("/order")
    public void completeOrder(@RequestParam("userId") Long userId) {
        User user = userService.getById(userId);
        orderService.completeOrder(user);
    }

    @GetMapping("/userOrders")
    List<OrderDto> getUserOrders(@RequestParam("userId") Long userId) {
        return orderService.getOrdersHistory(userId)
                .stream()
                .map(this::transformToDto)
                .collect(Collectors.toList());
    }

    private OrderDto transformToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setTicketsDto(order.getTickets()
                .stream()
                .map(this::transformToTicketsDto)
                .collect(Collectors.toList()));
        orderDto.setOrderDate(order.getOrderDate());
        return orderDto;
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
