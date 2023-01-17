package ruby.springbasic.componentScan;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ruby.springbasic.AppConfig;
import ruby.springbasic.multiple.MultipleService;
import ruby.springbasic.multiple.TestPolicy;
import ruby.springbasic.multiple.TestPolicyImpl1;
import ruby.springbasic.multiple.TestPolicyImpl2;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MultipleInjectionTest {

    @Test
    void testListInjection() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MultipleService multipleService = ac.getBean(MultipleService.class);
        TestPolicyImpl1 testPolicy1 = ac.getBean(TestPolicyImpl1.class);
        TestPolicyImpl2 testPolicy2 = ac.getBean(TestPolicyImpl2.class);
        List<TestPolicy> testPolicies = multipleService.getTestPolicies();

        assertThat(testPolicies.size()).isEqualTo(2);
        assertThat(testPolicies).contains(testPolicy1, testPolicy2);
    }

    @Test
    @DisplayName("TestName")
    void testMapInjection() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MultipleService multipleService = ac.getBean(MultipleService.class);
        Map<String, TestPolicy> policyMap = multipleService.getPolicyMap();
        TestPolicy testPolicyImpl1 = policyMap.get("testPolicyImpl1");
        TestPolicy testPolicyImpl2 = policyMap.get("testPolicyImpl2");

        assertThat(policyMap.size()).isEqualTo(2);
        assertThat(testPolicyImpl1).isNotNull();
        assertThat(testPolicyImpl2).isNotNull();
    }
}
