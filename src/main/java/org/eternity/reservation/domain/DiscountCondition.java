package org.eternity.reservation.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class DiscountCondition {
    public enum ConditionType { PERIOD_CONDITION, SEQUENCE_CONDITION, COMBINE_CONDITION }

    private Long id;
    private Long policyId;
    private ConditionType conditionType;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer sequence;

    public boolean isSatisfiedBy(Screening screening) {
        if (isPeriodCondition()){
            return screening.isPlayedIn(this.dayOfWeek,
                    this.startTime,
                    this.endTime);
        } else if(isSequenceCondition()){
            return this.sequence.equals(screening.getSequence());
        } else if (isCombineCondition()){
            return (this.sequence.equals(screening.getSequence())) &&
                    (screening.isPlayedIn(this.dayOfWeek, this.startTime, this.endTime));
        }

        return false;
    }

    public DiscountCondition(){
    }

    public DiscountCondition(Long policyId, ConditionType conditionType, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime, Integer sequence) {
        this(null, policyId,conditionType, dayOfWeek, startTime, endTime, sequence);
    }

    public DiscountCondition(Long id, Long policyId, ConditionType conditionType, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime, Integer sequence) {
        this.id = id;
        this.policyId = policyId;
        this.conditionType = conditionType;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sequence = sequence;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    private boolean isPeriodCondition() {
        return ConditionType.PERIOD_CONDITION.equals(conditionType);
//        return conditionType == ConditionType.PERIOD_CONDITION;
    }

    private boolean isSequenceCondition() {
        return ConditionType.SEQUENCE_CONDITION.equals(conditionType);
    }

    private boolean isCombineCondition() {
        return ConditionType.COMBINE_CONDITION.equals(conditionType);
    }

    public ConditionType getConditionType() {
        return conditionType;
    }

    public void setConditionType(ConditionType conditionType) {
        this.conditionType = conditionType;
    }

//    public DayOfWeek getDayOfWeek() {
//        return dayOfWeek;
//    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

//    public LocalTime getStartTime() {
//        return startTime;
//    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

//    public LocalTime getEndTime() {
//        return endTime;
//    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

//    public Integer getSequence() {
//        return sequence;
//    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
