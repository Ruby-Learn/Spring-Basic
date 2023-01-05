package ruby.springbasic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ruby.springbasic.discount.DiscountPolicy;
import ruby.springbasic.discount.RateDiscountPolicy;
import ruby.springbasic.member.MemberRepository;
import ruby.springbasic.member.MemberService;
import ruby.springbasic.member.MemberServiceImpl;
import ruby.springbasic.member.MemoryMemberRepository;
import ruby.springbasic.order.OrderService;
import ruby.springbasic.order.OrderServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
