package ruby.springbasic;

import ruby.springbasic.discount.DiscountPolicy;
import ruby.springbasic.discount.RateDiscountPolicy;
import ruby.springbasic.member.MemberRepository;
import ruby.springbasic.member.MemberService;
import ruby.springbasic.member.MemberServiceImpl;
import ruby.springbasic.member.MemoryMemberRepository;
import ruby.springbasic.order.OrderService;
import ruby.springbasic.order.OrderServiceImpl;

/**
 * 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다.
 * 생성한 객체 인스턴스의 참조를 생성자를 통해서 주입시켜준다.
 * '사용할 객체의 생성' 및 '의존관계 주입'이라는 분리한 관심사를 맡아 책임지는 역할을 담당하는 클래스
 *  - Spring 의 DI 컨테이너 역할을 담당
 */
public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    /** 할인 정책을 변경시 해당 부분만 변경하면 된다. */
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
