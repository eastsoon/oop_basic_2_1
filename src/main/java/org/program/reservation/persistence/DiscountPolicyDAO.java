package org.program.reservation.persistence;

import org.program.reservation.domain.DiscountPolicy;

public interface DiscountPolicyDAO {
    DiscountPolicy selectDiscountPolicy(Long movieId);

    void insert(DiscountPolicy discountPolicy);
}
