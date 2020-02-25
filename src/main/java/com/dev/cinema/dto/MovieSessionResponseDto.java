package com.dev.cinema.dto;

import javax.validation.constraints.NotNull;

public class MovieSessionResponseDto {
    @NotNull
    private String movieTitle;
    @NotNull
    private Long cinemaHallId;
    @NotNull
    private String showTime;

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
}
