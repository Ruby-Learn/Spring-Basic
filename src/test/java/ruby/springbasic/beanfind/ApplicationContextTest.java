package ruby.springbasic.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ruby.springbasic.AppConfig;

import java.util.Arrays;

public class ApplicationContextTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEach(beanName -> {
            Object bean = ac.getBean(beanName);
            System.out.println("name = " + beanName + " object = " + bean);
        });
    }

    @Test
    @DisplayName("애픓리케이션 빈 출력")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames)
                .filter(beanName -> {
                    BeanDefinition beanDefinition = ac.getBeanDefinition(beanName);
                    return beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION;
                })
                .forEach(beanName -> {
                    Object bean = ac.getBean(beanName);
                    System.out.println("name = " + beanName + " object = " + bean);
                });
    }

    @Test
    @DisplayName("스프링이 내부에서 사용하는 빈 출력")
    void findInfrastructureBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames)
                .filter(beanName -> {
                    BeanDefinition beanDefinition = ac.getBeanDefinition(beanName);
                    return beanDefinition.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE;
                })
                .forEach(beanName -> {
                    Object bean = ac.getBean(beanName);
                    System.out.println("name = " + beanName + " object = " + bean);
                });
    }
}
