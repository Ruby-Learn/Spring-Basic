package ruby.springbasic.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
