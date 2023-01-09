## @ComponentScan
- 지정한 범위 내의 @Component 가 붙은 모든 클래스를 빈 객체로 생성하여 등록하는 애너테이션
- @SpringBootApplication 은 @ComponentScan 을 포함하고 있다.
- @ComponentScan 의 속성
  - basePackages
    - 지정한 패키지 및 하위 패키지에 있는 모든 @Component 이 붙은 클래스들을 빈 객체로 등록
    - 패키지를 지정하지 않으면 기본값으로 @ComponentScan 이 붙은 클래스의 패키지를 기준으로 함
      - 이러한 특성을 활용하여 @ComponentScan 이 붙은 클래스를 프로젝트 최상단에 두어서 프로젝트 내 모든 패키지를 대상으로 하는 것을 권장함  
  - includeFilters
    - 지정한 대상을 컴포넌트 스캔 대상에 포함
  - excludeFilters
    - 지정한 대상을 컴포넌트 스캔 대상에서 제외
  ```java
  @Target(ElementType.TYPE)
  @Retention(RetentionPolicy.RUNTIME)
  @Document
  public @interface IncludeComponent {}
  
  @Target(ElementType.TYPE)
  @Retention(RetentionPolicy.RUNTIME)
  @Document
  public @interface ExcludeComponent {}
  
  @Configuration
  @ComponentScan(
          excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = IncludeComponent.class),
          excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = ExcludeComponent.class)
  )
  public class AppConfig {}
  
  ```
  
<br>

## @Autowired
- @Component 를 붙인 클래스 내의 필드, 또는 생성자, 수정자에 사용. @Autowired 가 붙은 필드, 메서드의 매개변수에 스프링 컨테이너에 등록되어 있는 빈 객체를 주입
  - 생성자를 활용한 의존성 주입을 권장함
    - 필드에 의존성을 주입하는 방법은 Setter 등의 방법으로 해당 필드가 다른 객체로 변경될 가능성이 존재함
    - 생성자를 통한 의존성 주입 방법은 필드를 final 로 선언할 수 있으므로 필드의 변경을 막을 수 있음
  ```java
  @Component
  public class MemberServiceImpl implements MemberService{
      
      // 필드에 의존성 주입
      @Autowired
      private MemberRepository memberRepository;
  
      @Override
      public void join(Member member) {
          memberRepository.save(member);
      }
  
      @Override
      public Member findMember(Long memberId) {
          return memberRepository.findById(memberId);
      }
  }
  
  @Component
  public class OrderServiceImpl implements OrderService{
  
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
  
    // 생성자의 매개변수에 의존성 주입
    @Autowired
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
  ```

