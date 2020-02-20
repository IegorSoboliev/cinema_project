package com.dev.cinema.dto;

import java.util.List;

public class ShoppingCartResponseDto {
    private Long userId;
    private List<TicketDto> ticketsDto;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<TicketDto> getTicketsDto() {
        return ticketsDto;
    }

    public void setTicketsDto(List<TicketDto> ticketsDto) {
        this.ticketsDto = ticketsDto;
    }
}
