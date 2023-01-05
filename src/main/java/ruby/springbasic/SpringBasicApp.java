package ruby.springbasic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringBasicApp {

    public static void main(String[] args) {

        /**
         * 스프링 컨테이너 생성
         * - AnnotationConfigApplicationContext - 애너테이션 기반의 자바 설정 클래스로 스프링 컨테이너를 생성
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    }

}
