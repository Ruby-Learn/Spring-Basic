package ruby.springbasic.multiple;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * List, Map 으로 타입에 해당하는 여러 빈을 주입받을 수 있다.
 */
@Service
@RequiredArgsConstructor
@Getter
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
