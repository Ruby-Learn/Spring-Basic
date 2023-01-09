package ruby.springbasic.discount;

import org.springframework.stereotype.Component;
import ruby.springbasic.member.Grade;
import ruby.springbasic.member.Member;

@Component
public class RateDiscountPolicy implements DiscountPolicy{

    private final int discountPercent = 10;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade().equals(Grade.VIP)) {
            return price * discountPercent / 100;
        }
        return 0;
    }
}
