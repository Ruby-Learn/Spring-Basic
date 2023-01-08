package ruby.springbasic.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ruby.springbasic.AppConfig;
import ruby.springbasic.member.MemberService;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링이 아닌 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        /**
         * 두 객체가 서로 다름. 즉, 클라이언트가 호출할 때마다 MemberService 타입의 새로운 객체가 생성됨
         * - 클라이언트의 수많은 요청이 발생하는 웹 환경에서는 메모리 낭비가 극심하여 적합하지 않음
         */
        assertThat(memberService1).isNotEqualTo(memberService2);
    }
}
