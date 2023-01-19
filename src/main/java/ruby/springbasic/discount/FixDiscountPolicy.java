package ruby.springbasic.discount;

import ruby.springbasic.member.Grade;
import ruby.springbasic.member.Member;

//@Component
public class FixDiscountPolicy implements DiscountPolicy{

    public static final int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade().equals(Grade.VIP)) {
            return discountFixAmount;
        }
        return 0;
    }
}
