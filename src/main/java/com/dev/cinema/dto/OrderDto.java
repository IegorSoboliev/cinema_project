package com.dev.cinema.dto;

import java.time.LocalDate;
import java.util.List;

public class OrderDto {
    private Long id;
    private List<TicketDto> ticketsDto;
    private LocalDate orderDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TicketDto> getTicketsDto() {
        return ticketsDto;
    }

    public void setTicketsDto(List<TicketDto> ticketsDto) {
        this.ticketsDto = ticketsDto;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
}
