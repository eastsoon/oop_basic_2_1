package org.program.reservation.persistence;

import org.program.reservation.domain.Reservation;

public interface ReservationDAO {
    void insert(Reservation reservation);
}
