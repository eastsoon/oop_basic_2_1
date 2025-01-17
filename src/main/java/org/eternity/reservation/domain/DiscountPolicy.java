package org.eternity.reservation.domain;

import org.eternity.generic.Money;

import java.util.ArrayList;
import java.util.List;

public class DiscountPolicy {
    public enum PolicyType { PERCENT_POLICY, AMOUNT_POLICY }

    private Long id;
    private Long movieId;
    private PolicyType policyType;
    private Money amount;
    private Double percent;
    private List<DiscountCondition> discountConditions;

    public Money calculateDiscount(Movie movie) {
        if(isAmountPolicy()){
            return amount;
        } else if (isPercentPolicy()){
            return movie.getFee().times(percent);
        }

        return Money.ZERO;
    }

    public DiscountPolicy(){
    }

    public DiscountPolicy(Long movieId, PolicyType policyType, Money amount, Double percent) {
        this(null, movieId, policyType, amount, percent, new ArrayList<>());
    }

    public DiscountPolicy(Long id, Long movieId, PolicyType policyType, Money amount, Double percent, List<DiscountCondition> discountConditions) {
        this.id = id;
        this.movieId = movieId;
        this.policyType = policyType;
        this.amount = amount;
        this.percent = percent;
        this.discountConditions = discountConditions;
    }

    public boolean findDiscountCondition(Screening screening){
        for(DiscountCondition discountCondition : discountConditions){
            if(discountCondition.isSatisfiedBy(screening)){
                return true;
            }
        }
        return false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    private boolean isAmountPolicy() {
        return PolicyType.AMOUNT_POLICY.equals(policyType);
    }

    private boolean isPercentPolicy() {
        return PolicyType.PERCENT_POLICY.equals(policyType);
    }

//    public PolicyType getPolicyType() {
//        return policyType;
//    }

    public void setPolicyType(PolicyType policyType) {
        this.policyType = policyType;
    }

//    public Money getAmount() {
//        return amount;
//    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

//    public Double getPercent() {
//        return percent;
//    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }
}
