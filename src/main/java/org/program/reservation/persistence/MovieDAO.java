package org.program.reservation.persistence;

import org.program.reservation.domain.Movie;

public interface MovieDAO {
    Movie selectMovie(Long movieId);

    void insert(Movie movie);
}
