## 의존관계 주입 방법
### 생성자 주입
- 셍성자를 통해서 의존관계를 주입받는 방법
  - 생성자 호출 시점에 단 한 번만 호출되는 것이 보장됨
  - 불변, 필수 의존관계에 사용
  - 의존관계가 중간에 변경되는 경우는 극히 드물기 때문에 생성자 주입 방식은 가장 권장되는 방식이기도 하다.
```java
@Component
public class OrderServiceImpl implements OrderService{

    // final 로 인하여 다른 객체로 변경되지 않음을 보장. 또한 생성자를 통해 필드를 반드시 초기화해야함으로 의존관계 누락을 막아준다.
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

### List / Map 을 통한 의존성 주입
- List, Map 을 활용하면 특정 타입에 해당하는 여러 빈을 주입받을 수 있다.
```java
public enum TestPolicyType {
    TEST1, TEST2
}

public interface TestPolicy {
  // 구현할 정책이 여러개일 경우 정책을 구분하기 위한 메서드
  boolean isPolicyType(TestPolicyType testPolicyType);
}

@Component
public class TestPolicyImpl1 implements TestPolicy{

  private static final TestPolicyType POLICY_TYPE = TestPolicyType.TEST1;

  @Override
  public boolean isPolicyType(TestPolicyType testPolicyType) {
    return testPolicyType.equals(POLICY_TYPE);
  }
}

@Component
public class TestPolicyImpl2 implements TestPolicy{
  private static final TestPolicyType POLICY_TYPE = TestPolicyType.TEST2;

  @Override
  public boolean isPolicyType(TestPolicyType testPolicyType) {
    return testPolicyType.equals(POLICY_TYPE);
  }
}

/**
 * List, Map 을 활용하면 외부로부터 사용할 빈을 구분하기 위한 값을 통해 선택하여 사용할 수 있다.
 * - 특정 비즈니스를 처리할 때 정책을 분기처리 해야할 때 유용하다.
 */
@Service
@RequiredArgsConstructor
public class MultipleService {

  private final List<TestPolicy> testPolicies;

  // Map 으로 의존성 주입을 받을 경우 기본 키 값은 빈의 타입명이 된다.(빈 이름의 첫문자는 소문자)
  private final Map<String, TestPolicy> policyMap;

  public TestPolicy getTestPolicyByList(TestPolicyType testPolicyType) {
    return testPolicies.stream()
            .filter(testPolicy -> testPolicy.isPolicyType(testPolicyType))
            .findFirst()
            .orElse(null);
  }

  public TestPolicy getTestPolicyByMap(String policyKey) {
    return policyMap.get(policyKey);
  }
}
```