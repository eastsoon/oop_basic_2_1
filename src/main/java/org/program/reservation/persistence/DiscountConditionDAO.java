package org.program.reservation.persistence;

import org.program.reservation.domain.DiscountCondition;

import java.util.List;

public interface DiscountConditionDAO {
    List<DiscountCondition> selectDiscountConditions(Long policyId);

    void insert(DiscountCondition discountCondition);
}
