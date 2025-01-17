package org.eternity.reservation.persistence.memory;

import org.eternity.reservation.domain.Screening;
import org.eternity.reservation.persistence.ScreeningDAO;

public class ScreeningMemoryDAO extends InMemoryDAO<Screening> implements ScreeningDAO {
    @Override
    public Screening selectScreening(Long screeningId) {
        return findOne(screening -> screening.getId().equals(screeningId));
    }
}
