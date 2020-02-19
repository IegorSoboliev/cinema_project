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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("orders")
public class OrderController {
    private OrderService orderService;
    private UserService userService;
    private ShoppingCartService shoppingCartService;

    @PostMapping("/complete")
    public void completeOrder(@RequestParam("userId") Long userId) {
        User user = userService.getById(userId);
        List<Ticket> tickets = shoppingCartService.getByUser(user).getTickets();
        orderService.completeOrder(tickets, user);
    }

    @GetMapping("/")
    List<OrderDto> getUserOrders(@RequestParam("userId") Long userId) {
        User user = userService.getById(userId);
        return orderService.getOrderHistory(user)
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
        ticketDto.setDateTime(movieSession.getShowTime());
        ticketDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        return ticketDto;
    }
}
