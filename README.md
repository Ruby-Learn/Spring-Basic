## 의존관계 주입 방법
### 생성자 주입
- 셍성자를 통해서 의존관계를 주입받는 방법
  - 생성자 호출 시점에 단 한 번만 호출되는 것이 보장됨
  - 불변, 필수 의존관계에 사용
  - 의존관계가 중간에 변경되는 경우는 극히 드물기 때문에 생성자 주입 방식은 가장 권장되는 방식이기도 하다.
```java
@Component
public class OrderServiceImpl implements OrderService{

    // final 로 인하여 다른 객체로 변경되지 않음을 보장
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // memberRepository, discountPolicy 는 빈 객체
    // 클래스 내에 생성자가 단 한 개만 있다만 @Autowired 를 생략할 수 있다.
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
}
```

### 필드 주입
- @Autowired 를 통해 필드에 직접 의존관계를 주입받는 방법
- 코드가 간결하다는 장점이 있지만 외부에서 변경이 불가능하므로 테스트하기 힘들며, DI 프레임워크에 의존하며 프레임워크가 없으면 동작할 수 없는 코드가 되는 등의
단점이 있어 필드 주입을 통한 의존관계 주입 방법은 권장되지 않는다.
  - 생성자 및 수정자 메서드가 없어 외부에서 필드에 접근할 방법이 없으므로 테스트시에 필드를 임의의 객체로 설정할 수 없다.
```java
@Component
public class MemberService {
    
    @Autowired
    private MemberRepository memberRepository;
    
}

```

### 수정자 주입, 일반 메서드
- 수정자, 일반 메서드를 통해서 의존관계를 주입받을 수 있다.
- 의존관계가 중간에 변경되는 경우는 극히 드물기 때문에 일반적으로 잘 쓰이지 않는다.
```java
@Component
public class OrderServiceImpl implements OrderService{

    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;
    
    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        
    }
}
```