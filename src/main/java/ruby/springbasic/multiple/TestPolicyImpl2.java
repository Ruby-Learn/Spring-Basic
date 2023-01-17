package ruby.springbasic.multiple;

import org.springframework.stereotype.Component;

@Component
public class TestPolicyImpl2 implements TestPolicy{
    private static final TestPolicyType POLICY_TYPE = TestPolicyType.TEST2;

    @Override
    public boolean isPolicyType(TestPolicyType testPolicyType) {
        return testPolicyType.equals(POLICY_TYPE);
    }
}
