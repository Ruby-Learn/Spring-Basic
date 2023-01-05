package ruby.springbasic.discount;

import ruby.springbasic.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
