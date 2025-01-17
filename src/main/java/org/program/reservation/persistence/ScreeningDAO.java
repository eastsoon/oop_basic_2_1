package org.program.reservation.persistence;

import org.program.reservation.domain.Screening;

public interface ScreeningDAO {
    Screening selectScreening(Long screeningId);

    void insert(Screening screening);
}
