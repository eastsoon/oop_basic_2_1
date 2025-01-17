package org.program.reservation.persistence.memory;

import org.program.reservation.domain.Reservation;
import org.program.reservation.persistence.ReservationDAO;

public class ReservationMemoryDAO extends InMemoryDAO<Reservation> implements ReservationDAO {
    @Override
    public void insert(Reservation reservation) {
        super.insert(reservation);
    }
}
