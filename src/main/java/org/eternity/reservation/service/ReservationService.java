package org.eternity.reservation.service;

import org.eternity.generic.Money;
import org.eternity.reservation.domain.*;
import org.eternity.reservation.persistence.*;

import java.util.List;

public class ReservationService {
    private ScreeningDAO screeningDAO;
    private MovieDAO movieDAO;
    private DiscountPolicyDAO discountPolicyDAO;
    private DiscountConditionDAO discountConditionDAO;
    private ReservationDAO reservationDAO;

    public ReservationService(ScreeningDAO screeningDAO,
                              MovieDAO movieDAO,
                              DiscountPolicyDAO discountPolicyDAO,
                              DiscountConditionDAO discountConditionDAO,
                              ReservationDAO reservationDAO
                            ) {
        this.screeningDAO = screeningDAO;
        this.movieDAO = movieDAO;
        this.discountPolicyDAO = discountPolicyDAO;
        this.discountConditionDAO = discountConditionDAO;
        this.reservationDAO = reservationDAO;
    }

    public Reservation reservationScreening(Long customerId, Long screeningId, Integer audienceCount){
        Screening screening = screeningDAO.selectScreening(screeningId);
        Movie movie = movieDAO.selectMovie(screening.getMovieId());
        DiscountPolicy discountPolicy = discountPolicyDAO.selectDiscountPolicy(movie.getId());
        boolean found = discountPolicy.findDiscountCondition(screening);

        Money fee;
        if(found){
//            fee = movie.getFee().minus(calculateDiscount(discountPolicy, movie));
            fee = movie.getFee().minus(discountPolicy.calculateDiscount(movie));
        } else{
            fee = movie.getFee();
        }

        Reservation reservation = makeReservation(customerId, screeningId, audienceCount, fee);
        reservationDAO.insert(reservation);

        return reservation;
    };

    private Reservation makeReservation(Long customerId, Long screeningId, Integer audienceCount, Money fee){
        return new Reservation(customerId, screeningId, audienceCount, fee.times(audienceCount));
    }
}
