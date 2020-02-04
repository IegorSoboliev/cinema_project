package com.dev.cinema;

import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.Movie;
import com.dev.cinema.service.MovieService;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {

        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        Movie movie = new Movie();
        movie.setTitle("Family instant");
        movie = movieService.add(movie);

        System.out.println(movie);
        try {
            movieService.getAll().forEach(System.out ::println);
        } catch (DataProcessingException e) {
        }
    }
}
