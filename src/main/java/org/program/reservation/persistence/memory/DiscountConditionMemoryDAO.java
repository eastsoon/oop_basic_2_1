package org.program.reservation.persistence.memory;

import org.program.reservation.domain.DiscountCondition;
import org.program.reservation.persistence.DiscountConditionDAO;

import java.util.List;

public class DiscountConditionMemoryDAO extends InMemoryDAO<DiscountCondition> implements DiscountConditionDAO {

    @Override
    public List<DiscountCondition> selectDiscountConditions(Long policyId) {
        return findMany(discountCondition -> discountCondition.getPolicyId().equals(policyId));
    }
}
