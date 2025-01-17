package org.program.reservation.persistence.memory;

import org.program.reservation.domain.Screening;
import org.program.reservation.persistence.ScreeningDAO;

public class ScreeningMemoryDAO extends InMemoryDAO<Screening> implements ScreeningDAO {
    @Override
    public Screening selectScreening(Long screeningId) {
        return findOne(screening -> screening.getId().equals(screeningId));
    }
}
