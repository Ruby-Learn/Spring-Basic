package ruby.springbasic.multiple;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class TestPolicyImpl1 implements TestPolicy{

    private static final TestPolicyType POLICY_TYPE = TestPolicyType.TEST1;

    @Override
    public boolean isPolicyType(TestPolicyType testPolicyType) {
        return testPolicyType.equals(POLICY_TYPE);
    }
}
