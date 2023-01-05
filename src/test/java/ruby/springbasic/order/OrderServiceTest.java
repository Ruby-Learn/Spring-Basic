package ruby.springbasic.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ruby.springbasic.AppConfig;
import ruby.springbasic.discount.FixDiscountPolicy;
import ruby.springbasic.member.Grade;
import ruby.springbasic.member.Member;
import ruby.springbasic.member.MemberService;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    void setUp() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        Member member = new Member(1L, "ruby", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(member.getId(), "플루트", 10000000);
        assertThat(order.getDiscountPrice()).isEqualTo(FixDiscountPolicy.discountFixAmount);
    }

}