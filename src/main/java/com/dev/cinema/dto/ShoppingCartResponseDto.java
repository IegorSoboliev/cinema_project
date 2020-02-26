package com.dev.cinema.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

public class ShoppingCartResponseDto {
    @NotNull
    private Long userId;
    private List<TicketDto> ticketsDto = new ArrayList<>();

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
