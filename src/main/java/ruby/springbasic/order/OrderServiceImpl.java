package ruby.springbasic.order;

import ruby.springbasic.discount.DiscountPolicy;
import ruby.springbasic.member.Member;
import ruby.springbasic.member.MemberRepository;

public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    /**
     * 할인 정책이 변경될 때마다 해당 부분을 변경해야 한다.
     * 이는 OrderServiceImpl 에서 인터페이스(DiscountPolicy)가 아닌 구현 클래스(FixDiscountPolicy)에 의존하고 있기 때문이다.
     * 이를 해결하려면 구현 클래스를 결정 및 생성하는 부분이 클래스 내부가 아닌 외부에서 결정되어야 한다.
     */
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private final DiscountPolicy discountPolicy;

    /**
     * 생성자를 통해 외부에서 구현 클래스의 객체를 주입받는다.
     * 즉, 사용할 구현 타입을 외부에서 결정하며 OrderServiceImpl 는 실행에만 집중하면 된다.
     */
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
