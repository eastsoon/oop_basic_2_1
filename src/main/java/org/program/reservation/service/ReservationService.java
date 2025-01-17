package org.program.reservation.service;

import org.program.generic.Money;
import org.program.reservation.domain.*;
import org.program.reservation.persistence.*;

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
        List<DiscountCondition> discountConditions = discountConditionDAO.selectDiscountConditions(discountPolicy.getId());

        DiscountCondition discountCondition = findDiscountCondition(screening, discountConditions);

        Money fee;
        if(discountCondition != null){
            fee = movie.getFee().minus(calculateDiscount(discountPolicy, movie));
        } else{
            fee = movie.getFee();
        }

        Reservation reservation = makeReservation(customerId, screeningId, audienceCount, fee);
        reservationDAO.insert(reservation);

        return reservation;
    };

    private DiscountCondition findDiscountCondition(Screening screening, List<DiscountCondition> conditions ){
        for(DiscountCondition discountCondition : conditions){
            if (discountCondition.isPeriodCondition()){
                if (screening.isPlayedIn(discountCondition.getDayOfWeek(),
                                        discountCondition.getStartTime(),
                                        discountCondition.getEndTime())) {
                    return discountCondition;
                }
            } else {
                if (discountCondition.getSequence().equals(screening.getSequence())){
                    return discountCondition;
                }
            }
        }

        return null;
    }

    private Money calculateDiscount(DiscountPolicy discountPolicy, Movie movie){
        if(discountPolicy.isAmountPolicy()){
            return discountPolicy.getAmount()
        } else if (discountPolicy.isPercentPolicy()) {
            return movie.getFee().times(discountPolicy.getPercent());
        }

        return Money.ZERO;
    }

    private Reservation makeReservation(Long customerId, Long screeningId, Integer audienceCount, Money fee){
        return new Reservation(customerId, screeningId, audienceCount, fee);
    }
}
