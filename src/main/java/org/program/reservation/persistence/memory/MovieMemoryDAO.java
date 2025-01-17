package org.program.reservation.persistence.memory;

import org.program.reservation.domain.Movie;
import org.program.reservation.persistence.MovieDAO;

public class MovieMemoryDAO extends InMemoryDAO<Movie> implements MovieDAO {
    @Override
    public Movie selectMovie(Long movieId) {
        return findOne(movie -> movie.getId().equals(movieId));
    }
}
