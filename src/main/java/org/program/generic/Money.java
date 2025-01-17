package org.program.generic;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

public class Money {

    // 상품 가격을 더하기, 빼기, 나누기, 곱하기를 제네릭으로 표현하여 다른 객체에서도 공통적으로 쓸수 있게끔하는 용도
    //

    // 가격이 0 원이라는 상태를 Mone 객체에 wons으로 제어
    public static final Money ZERO = Money.wons(0);

    public final BigDecimal amount;

    public static Money wons(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money wons(double amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static <T> Money sum(Collection<T> bags, Function<T, Money> monetary){
//        return bags.stream().map(bag -> monetary.apply(bag)).reduce(Money.ZERO, Money::plus);
//        return bags.stream().map(monetary::apply).reduce(Money.ZERO, Money::plus);
        return bags.stream().map(monetary).reduce(Money.ZERO, Money::plus);
    }

    Money(BigDecimal amount) {
        this.amount = amount;
    }

    public Money plus(Money money) {
        return new Money(this.amount.add(money.amount));
    }

    public Money minus(Money money) {
        return new Money(this.amount.subtract(money.amount));
    }
    public Money times(double percent) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(percent)));
    }

    public Money divide(double divisor) {
        return new Money(this.amount.divide(BigDecimal.valueOf(divisor)));
    }

    public boolean isLessThan(Money other) {
        return this.amount.compareTo(other.amount) < 0;
    }

    public boolean isGreaterThan(Money other) {
        return this.amount.compareTo(other.amount) > 0;
    }

    public boolean isGreaterThanOrEqual(Money other) {
        return this.amount.compareTo(other.amount) <= 0;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Long logValue() {
        return this.amount.longValue();
    }

    public Double doubleValue() {
        return this.amount.doubleValue();
    }

    public String toString() {
        return amount.toString() + "원";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Money money = (Money) other;
        return Objects.equals(amount.doubleValue(), money.amount.doubleValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount.doubleValue());
    }
}
