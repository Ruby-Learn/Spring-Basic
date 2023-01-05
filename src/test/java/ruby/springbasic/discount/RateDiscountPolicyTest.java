package ruby.springbasic.discount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ruby.springbasic.member.Grade;
import ruby.springbasic.member.Member;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("회원 등급이 VIP 가 아닐 경우 할인 미적용")
    void rateDiscount_basicGrade() {
        Member member = new Member(1L, "ruby", Grade.BASIC);

        int discount = discountPolicy.discount(member, 10000);

        assertThat(discount).isEqualTo(0);
    }

    @Test
    @DisplayName("회원 등급이 VIP 일 경우 10% 할인 적용")
    void rateDiscount_vipGrade() {
        Member member = new Member(1L, "ruby", Grade.VIP);

        int discount = discountPolicy.discount(member, 10000);

        assertThat(discount).isEqualTo(1000);
    }
}