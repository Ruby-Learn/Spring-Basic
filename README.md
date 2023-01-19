## 스프링 빈의 이벤트 라이프사이클
- 스프링 빈 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 빈 사용 -> 소멸 전 콜백 -> 스프링 종료

### 초기화 콜백
- 빈 객체가 생성되고, 빈의 의존관계 주입이 완료된 후 호출
- 생성자에서도 초기화 작업을 할 수 있지만 책임의 분리라는 측면에서 객체의 생성과 초기화를 각각 분리하는 것 유지보수 측면에서 더 좋다.

### 소멸 전 콜백
- 빈 객체가 소멸되기 직전에 호출

### 초기화 / 소멸 메서드 지정
- 초기화 / 소멸 메서드에 @PostConstruct / @PreDestroy 애너테이션 사용
  - 간단하게 초기화 / 소멸 메서드를 지정할 수 있지만 외부 라이브러리에는 사용할 수 없다.
    - 외부 라이브러리의 초기화 / 소멸 메서드 지정은 @Bean 의 initMethod / destroyMethod 속성을 통해 지정한다.
```java
public class NetworkClient {

    private final static Logger logger = LoggerFactory.getLogger(NetworkClient.class);

    private String url;

    public NetworkClient() {
        logger.info("NetworkClient Constructor");
    }

    /** PostConstruct - 빈 객체가 생성되고, 빈의 의존관계 주입이 완료된 후 호출 */
    @PostConstruct
    public void init() {
        logger.info("NetworkClient Init");
        connect();
        call("초기화 연결 메시지");
    }

    /** PreDestroy - 빈 객체가 소멸되기 직전에 호출 */
    @PreDestroy
    public void close() {
        logger.info("NetworkClient close");
        disconnect();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        logger.info("connect: " + url);
    }

    public void call(String message) {
        logger.info("call: {} / message: {}", url, message);
    }

    public void disconnect() {
        logger.info("close: {}", url);
    }
}

@Configuration
class LifeCycleConfig {
    @Bean
    public NetworkClient networkClient() {
        NetworkClient networkClient = new NetworkClient();
        networkClient.setUrl("https://ruby.com");
        return networkClient;
    }
}
```

- @Bean 의 initMethod, destroyMethod 속성 지정
  - @PostConstruct / @PreDestroy 와는 달리 외부 라이브러리 내부의 메서드를 초기화 / 소멸 메서드로 지정할 수 있다는 장점이 있다.
    - 외부 라이브러리의 초기화 / 소멸 시 동작이 필요한 메서드가 있을 때 해당 방법이 유용하다.
```java
public class NetworkClient {

    private final static Logger logger = LoggerFactory.getLogger(NetworkClient.class);

    private String url;

    public NetworkClient() {
        logger.info("NetworkClient Constructor");
    }

    public void init() {
        logger.info("NetworkClient Init");
        connect();
        call("초기화 연결 메시지");
    }

    public void close() {
        logger.info("NetworkClient close");
        disconnect();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        logger.info("connect: " + url);
    }

    public void call(String message) {
        logger.info("call: {} / message: {}", url, message);
    }

    public void disconnect() {
        logger.info("close: {}", url);
    }
}

@Configuration
class LifeCycleConfig {
    @Bean(initMethod = "init", destroyMethod = "close")
    public NetworkClient networkClient() {
        NetworkClient networkClient = new NetworkClient();
        networkClient.setUrl("https://ruby.com");
        return networkClient;
    }
}
```