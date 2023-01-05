package ruby.springbasic.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ruby.springbasic.AppConfig;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    void setUp() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }


    @Test
    void join() {
        Member member = new Member(1L, "ruby", Grade.VIP);

        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        assertThat(member).isEqualTo(findMember);
    }
}
