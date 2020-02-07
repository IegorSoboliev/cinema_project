package com.dev.cinema.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dev.cinema.dao.OrderDao;
import com.dev.cinema.dao.ShoppingCartDao;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private static OrderDao orderDao;
    @Inject
    private static ShoppingCartDao shoppingCartDao;

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setUser(user);
        order.setTickets(tickets);
        orderDao.add(order);
        ShoppingCart shoppingCart = shoppingCartDao.getByUser(user);
        shoppingCart.setTickets(new ArrayList<Ticket>());
        shoppingCartDao.update(shoppingCart);
        return order;
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getOrderHistory(user);
    }
}
